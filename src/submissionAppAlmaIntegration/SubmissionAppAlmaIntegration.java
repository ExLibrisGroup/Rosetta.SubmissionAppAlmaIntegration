package submissionAppAlmaIntegration;


import gov.loc.mets.FileType;
import gov.loc.mets.MetsDocument;
import gov.loc.mets.MetsType.FileSec.FileGrp;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;

import org.apache.commons.io.FilenameUtils;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.apache.commons.codec.binary.Base64;

import resourceModel.ERESearchResultsSet;
import resourceModel.SRWRecord;
import srw.schema.x1.dcSchema.DcDocument;
import almarRestModels.Item;
import almarRestModels.RequestStatus;
import almarRestModels.RequestTypes;
import almarRestModels.UserRequest;
import almarRestModels.UserRequest.TargetDestination;

import com.exlibris.core.infra.common.shared.dataObjects.KeyValuePair;
import com.exlibris.core.infra.common.util.IOUtil;
import com.exlibris.core.sdk.consts.Enum;
import com.exlibris.core.sdk.exceptions.IEWSException;
import com.exlibris.core.sdk.formatting.DublinCore;
import com.exlibris.core.sdk.formatting.DublinCoreFactory;
import com.exlibris.core.sdk.parser.IEParserException;
import com.exlibris.core.sdk.utils.DepositDirUtil;
import com.exlibris.core.sdk.utils.FileUtil;
import com.exlibris.digitool.common.dnx.DnxDocument;
import com.exlibris.digitool.common.dnx.DnxDocumentFactory;
import com.exlibris.digitool.common.dnx.DnxDocumentHelper;
import com.exlibris.digitool.deposit.service.xmlbeans.DepositResultDocument;
import com.exlibris.digitool.deposit.service.xmlbeans.DepositResultDocument.DepositResult;
import com.exlibris.digitool.exceptions.DigitoolException;
import com.exlibris.digitool.exceptions.RepositoryException;
import com.exlibris.dps.sdk.deposit.DepositWebServices;
import com.exlibris.dps.sdk.deposit.IEParser;
import com.exlibris.dps.sdk.deposit.IEParserFactory;
import com.exlibris.dps.sdk.pds.HeaderHandlerResolver;



public class SubmissionAppAlmaIntegration  {
	
	static Logger log= new Logger();
	private static String almaUrl = null;
	private static String ApiKey = null;
	private static String digitizationDeptCode = null;
	private static String rosettaMFId = "";
	private static String rosettaProducerId = "";
	private static String rosettaPasswordAuthentication   = "";
	private static String scannedFilesNFSLocation= "";
	private static String SIPNFSLocation ;
	private static String mmsValue="";
	private static String holdingValue="";
	private static String itemValue="";
	private static String requestValue="";
	private static Properties fileConfigProperties;
	private static DublinCore dcRecord;
	
	private final static String ALMA_REQUEST = "ALMA_REQUEST";
	private final static String ALMA_MMS = "ALMA_MMS";
	private static final String POST_METHOD = "POST";
	private static final String GET_METHOD = "GET";
	private static final String TITLE = "dc:title";
	private static final String EXTERNAL_SYSTEM = "dc:externalSystem";
	private static final String EXTERNAL_ID = "dc:externalId";
	private static final String OPEN_STATUS ="In Process";
	private static final String DC_PREFIX ="dc:record";
	private static final String SCHEMA_LOCATION ="xsi:schemaLocation";
	private static final String DC_NAMESPACE =" xmlns:dc=\"http://purl.org/dc/elements/1.1/\"";
	
	//RESPONSE STATUS CODE
	private final static int OK_CODE= 200;
	
	private static String sruUrl;
	private static String streamsPath;
	private static String rosettaUrl;
	private static String userName;
	private static String inst;


	public static void main(String[] args) {
				
			try {
				init(args);			
				readAllBarcode();
			} catch (IOException e) {
				System.exit(1);
			}
			
	}
	
	/**
	 * readAllBarcode - Function that will read the barcodes.
	 */
	private static void readAllBarcode() {
		
		try{
			String barcodeListpath = fileConfigProperties.getProperty("barcodeListPath");
			final File barcodes = new File(barcodeListpath);
			if(!barcodes.exists()){
				log.error("No such file "+barcodeListpath);
				System.exit(1);
			}
			FileReader fileReader = new FileReader(barcodes);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String barcode;
			while ((barcode = bufferedReader.readLine()) != null) {
				try{
					log.info("Start to create mets file for barcode id: "+barcode);
					
					/**Get the mms_id ,holding_id,pid and initialize the variables**/
					getIdsfromAlmaApi(barcode);
					/**Get the dc record from sru request**/
					getDCrecordFromSru(barcode);
					/**Creates Alma digitization request for item pid **/
					createItemRequest(barcode);
					
					createMetsFile(barcode);
					/**Submit using Basic Authentication **/
					depositMets(barcode);
					log.info("Finished to create mets file for barcode id: "+barcode);
				}catch(Exception e){
					log.error("Failed to create mets file for barcode id: "+barcode);
				}
			
			}
			
		} catch (Exception e) {
			System.exit(1);
		}		
	}
	
	/**
	 * depositMets - Function that deposit the files using Basic Authentication.
	 * @param barcode
	 * @throws XmlException 
	*/
	private static void depositMets(String barcode) throws MalformedURLException, XmlException {
		try {
			// Set Authentication Header on service
			DepositWebServices_Service service = new DepositWebServices_Service(getIEWsdlUrl(), new QName("http://dps.exlibris.com/", "DepositWebServices"));
			service.setHandlerResolver(new HeaderHandlerResolver(userName, new String(Base64.decodeBase64(rosettaPasswordAuthentication)), inst));
			DepositWebServices depWebServices = service.getPort(new QName("http://dps.exlibris.com/", "DepositWebServicesPort"), DepositWebServices.class);
			String retval = depWebServices.submitDepositActivity(null, rosettaMFId, barcode, rosettaProducerId, null);
			DepositResultDocument depositResultDocument;
			depositResultDocument = DepositResultDocument.Factory.parse(retval);	
			DepositResult depositResult = depositResultDocument.getDepositResult();
			if(depositResult.getIsError()){
				log.error("Failed to deposit "+"(barcode id: "+barcode+")");
			}else{
				String sipID = String.valueOf(depositResult.getSipId());
				log.info("SIP_ID: "+sipID +" for barcode: "+barcode);
			}
		} catch (XmlException e) {
			log.error("Failed to deposit "+"(barcode id: "+barcode+")");
			throw e;
		}
	}
	
	public static URL getIEWsdlUrl() throws MalformedURLException  {
		String DEPOSIT_WSDL_URL = "http://" + rosettaUrl + "/dpsws/deposit/DepositWebServices?wsdl";
		URL urlWsdl = new URL(DEPOSIT_WSDL_URL);
		return urlWsdl;
	}
	
	/**
	 * createMetsFile - Create the mets file and the dc.xml file .
	 * @param barcode - the current barcode.
	 */
	private static void createMetsFile(String barcode) {
		
		
		try{
			IEParser ieParser = IEParserFactory.create();
			/** Set DublinCore Section **/
			try{
				ieParser.setIEDublinCore(dcRecord);
			}catch(Exception e){
				log.error("Failed to added the dc record to the DNX. "+"(barcode id: "+barcode+") "+e.getMessage());
				throw e;
			}
			/** Prepare IE DNX  **/
			DnxDocument ieDnx = DnxDocumentFactory.getInstance().createDnxDocument();
			DnxDocumentHelper ieDnxDocumentHelper = new DnxDocumentHelper(ieDnx);
			
			/** Add ALMA_MMS and ALMA_REQUEST to submission METS as IE ObjectIdentifier**/
			try{
				addOiFromDnx(ieDnxDocumentHelper,ALMA_MMS, mmsValue);			
				if(!requestValue.isEmpty()){
					addOiFromDnx(ieDnxDocumentHelper,ALMA_REQUEST, requestValue);
				}
			}catch(Exception e){
				log.error("Failed to added the object identifiers to the DNX. "+"(barcode id: "+barcode+") "+e.getMessage());
				throw e;
			}
			ieParser.setIeDnx(ieDnx);
						
			/** Prepare Representation DNX with GeneralRepCharacteristics Section **/
			FileGrp fileGrp = ieParser.addNewFileGrp(Enum.UsageType.VIEW, Enum.PreservationType.PRESERVATION_MASTER);
			DnxDocumentHelper repDnxDocumentHelper = new DnxDocumentHelper(ieParser.getFileGrpDnx(fileGrp.getID()));
			ieParser.setFileGrpDnx(repDnxDocumentHelper.getDocument(), fileGrp.getID());
			
			
			/** Prepare Target Directory **/
			File mainDirectory = new File(SIPNFSLocation);
			if (!mainDirectory.exists()) {
				mainDirectory.mkdir();
			}
			File barcodeDirectory = new File(SIPNFSLocation+"/"+barcode);
			if (barcodeDirectory.exists()) {
				barcodeDirectory.delete();
			}
			barcodeDirectory.mkdir();
			/** Create content Directory **/
			File contentDirectory = new File(FilenameUtils.concat(barcodeDirectory.getPath(), DepositDirUtil.CONTENT_DIR));
			contentDirectory.mkdir();
			/** Create stream Directory **/
			File streamsDirectory = new File(FilenameUtils.concat(contentDirectory.getPath(), DepositDirUtil.STREAMS_DIR));
			streamsDirectory.mkdir();
			streamsPath = streamsDirectory.getPath();
			
			
			/** Handle Files **/
			File filesDirectory = new File(scannedFilesNFSLocation+"/"+barcode);
			FileUtil.copyFilesToDirectory(filesDirectory, streamsDirectory, true);
			handleFiles(ieParser, fileGrp, streamsDirectory);
			
			/** Create METS File **/
			MetsDocument metsDoc = MetsDocument.Factory.parse(ieParser.toXML());
			File xmlFile = new File(FilenameUtils.concat(contentDirectory.getAbsolutePath(), DepositDirUtil.METS_XML));
			XmlOptions opt = new XmlOptions();
			opt.setSavePrettyPrint();
			FileUtil.writeFile(xmlFile, metsDoc.xmlText(opt));
			
			/** Create dc.xml File **/
			String dcString = createDcXml(barcode);
			if(dcString != null){
				addDcXmlFile(dcString, DepositDirUtil.DC_XML, barcodeDirectory.getPath());
			}
		}catch(Exception e){
			log.error(e.getMessage());
			System.exit(1);
		}					
	}
	
	
	private static String createDcXml(String barcode) throws Exception {
		try {
			DublinCore dc = DublinCoreFactory.getInstance().createDocument();
			dc.addElement(TITLE, "alma"+barcode);
			dc.addElement(EXTERNAL_SYSTEM, "alma");
			dc.addElement(EXTERNAL_ID, barcode);
			return dc.toXml().toString();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	/**
	 * copy string into a file and place it in any dir
	 *
	 * @param fileContents - contents to write into file
	 * @param fileNameWithExt - name of file, inc. extension to copy string into
	 * @param targetDir
	 * @return @throws
	 *         IOException
	 */
	public static boolean addDcXmlFile(String fileContents, String fileNameWithExt, String targetDir)
			throws IOException {
		File file = new File(targetDir +"/"+ fileNameWithExt);
		file.getParentFile().mkdirs();
		if (targetDir == null)
			return false;
		BufferedOutputStream m_buffOutStream = new BufferedOutputStream(new FileOutputStream(file.getAbsolutePath()));
		IOUtil.copy(fileContents, m_buffOutStream);
		m_buffOutStream.close();
		return true;
	}
	
	/**
	 * handleFiles - Create fileSec for the files from the scannedFilesNFSLocation .
	 * @param ieParser.
	 * @param fileGrp.
	 * @param streamsDirectory - target directory.
	 * @return - return true/false is success/failed to read the ids.
	 */
	private static Map<String, KeyValuePair<String, String>> handleFiles(IEParser ieParser, FileGrp fileGrp, File streamsDirectory) throws IEParserException {
		File[] files = streamsDirectory.listFiles();
		Arrays.sort(files);
		Map<String, KeyValuePair<String, String>> filesStreams = new LinkedHashMap<String, KeyValuePair<String, String>>();
		for (File file : files) {
			if (file.isFile()) {
				String filePid = ieParser.getFileId(fileGrp);
				String fileLabel = FilenameUtils.removeExtension(file.getName());
				String fileLocation = file.getPath().substring(streamsDirectory.getPath().length() + 1);
				filesStreams.put(filePid, new KeyValuePair<String, String>(
						fileLabel, fileLocation));
				FileType fileType = ieParser.addNewFile(fileGrp, null, fileLocation, fileLabel);
				// add dnx - A new DNX is constructed and added on the file level
				DnxDocument dnx = ieParser.getFileDnx(fileType.getID());
				DnxDocumentHelper fileDocumentHelper = new DnxDocumentHelper(dnx);
				fileDocumentHelper.getGeneralFileCharacteristics().setFileOriginalPath(fileLocation);
				ieParser.setFileDnx(fileDocumentHelper.getDocument(), fileType.getID());
			} else {
				filesStreams.putAll(handleFiles(ieParser, fileGrp, file));
			}
		}
	return filesStreams;
	}

	private static void init(String[] args) throws IOException {
		
		FileInputStream fis;	
		try {
			//read the file configuration
			fis = new FileInputStream(new File(args[0]));	
			fileConfigProperties = new Properties();
			fileConfigProperties.load(fis);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
			throw e;
		}
		catch (IOException e) {
			log.error("can't load the file configuration.");
			throw e;
		}
		initParams();		
	}

	/**
	 * createItemRequest - Creates Alma digitization request for item pid .
	 * @param barcode.
	 */
	private static void createItemRequest(String barcode) throws IOException {
		String xmlResponse = "";
		String requestUrl = almaUrl+"/bibs/"+mmsValue+"/holdings/"+holdingValue+"/items/"+itemValue+"/requests";
		HttpURLConnection con = null;     	
		InputStream is = null;
	   	BufferedReader reader = null;
	   	
	   	//Initialize the request variable.
		UserRequest request  = new UserRequest();
		request.setRequestType(RequestTypes.DIGITIZATION);
		TargetDestination targetDest = new TargetDestination();
		targetDest.setValue(digitizationDeptCode);
		request.setTargetDestination(targetDest);
		request.setPartialDigitization(false);
		StringWriter sw = new StringWriter();
		JAXB.marshal(request, sw);
       
        try {
			con = callAlmaApi(con,POST_METHOD,requestUrl,sw.toString().replaceAll("userRequest", "user_request"));	
			xmlResponse = returnResponseContent(con,is,reader);
			request = JAXB.unmarshal(new StringReader(xmlResponse), UserRequest.class);
			requestValue = request.getRequestId();
			
			//Scan-in item using request_id.
			String scanUrl ="https://api-na.hosted.exlibrisgroup.com/almaws/v1/bibs/"+mmsValue+"/holdings/"+holdingValue+"/items/"+itemValue+"?op=scan&department=DIGI_DEPT_INST&request_id="+requestValue;
			con = callAlmaApi(con,POST_METHOD,scanUrl,"");
			xmlResponse = returnResponseContent(con,is,reader);
			
			//move the item to the next step using request_id.
			String nextStepUrl =requestUrl+"/"+requestValue+"?op=next_step";
			con = callAlmaApi(con,POST_METHOD,nextStepUrl,"");			
			xmlResponse = returnResponseContent(con,is,reader);
			request = JAXB.unmarshal(new StringReader(xmlResponse), UserRequest.class);		
			RequestStatus requestStatus = request.getRequestStatus();
			
			//if the status is 'in process' we added the request to the dnx.
			if(!requestStatus.value().equals(OPEN_STATUS)){
				requestValue= "";
			}

        } catch (Exception e) {
			log.error("Failed to create request "+"(barcode id: "+barcode+") "+e.getMessage());
			requestValue= "";
		}      
	}

	/**
	 * getDCrecordFromSru - Gets DC record from SRU by MMS_ID.
	 * @param barcode - the current barcode.
	 */
	private static void getDCrecordFromSru(String barcode) throws Exception {
		String url = sruUrl + "/view/sru/TR_INTEGRATION_INST?version=1.2&operation=searchRetrieve&recordSchema=dc&query=alma.mms_id="+mmsValue;	
		String inputLine;
        String urlContent = "";
		try {
			URL connection = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.openStream())); 

			while ((inputLine = in.readLine()) != null)
	        	urlContent+=inputLine;
	        in.close();
        	log.info("dc content: \n"+urlContent);
        	if(urlContent.isEmpty()){
        		log.error("dc content is empty ");
        		throw new Exception();
        	}
        	ERESearchResultsSet searchResults =  new ERESearchResultsSet(urlContent);
    		if(searchResults != null){
	        	List<SRWRecord> resultsObjects=searchResults.getResultsObjects();		
				if (resultsObjects != null && resultsObjects.size() > 0){
					for ( int set = 0; set < resultsObjects.size(); set++){
						SRWRecord srwRecord = resultsObjects.get(set);
						converdSRWtoDC(srwRecord.getData() ,barcode);
					}
				}
    		}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	private static void converdSRWtoDC(String xml, String barcode)throws Exception  {

	
		final String dcRecordClose =DC_PREFIX;
		DcDocument dcDoc;
		log.info("Starting to parse barcode: "+barcode+" to dc format");
		try {
			dcDoc = DcDocument.Factory.parse(xml);
		} catch (XmlException e1) {
			log.error("Coudn't parse CMS-record: "+barcode+" to dc format");
			log.error(e1.getMessage());
			throw new IEWSException(e1);
		}
		
		// parse
		try {
			String oldNamespace=org.apache.commons.lang.StringUtils.substringBetween(xml,"<" ,">");
			String oldPrefixNamespace =org.apache.commons.lang.StringUtils.substringBetween(xml,"<" ," ");
			if(oldNamespace.contains(SCHEMA_LOCATION)){
				Pattern p = Pattern.compile("(xsi:schemaLocation=\"" + ".*?" + "\" )",Pattern.DOTALL);
				Matcher m = p.matcher(oldNamespace);
				if(m.find()) {
					String schemaLocation  = m.group(1);
					xml=xml.replaceFirst(schemaLocation, " ");
				}
			}
			if(oldNamespace.contains("xmlns:dc")){
				xml=xml.replaceFirst(oldPrefixNamespace, dcRecordClose);
			}else{
				xml=xml.replaceFirst(oldPrefixNamespace, dcRecordClose + DC_NAMESPACE);
			}
			
			String oldClose=xml.substring(org.apache.commons.lang.StringUtils.lastIndexOf(xml, "<")+2, org.apache.commons.lang.StringUtils.lastIndexOf(xml, ">"));
			xml= xml.replaceAll(oldClose, dcRecordClose);
			//create empty DublinCore document
			DublinCore dc = DublinCoreFactory.getInstance().createDocument();
			parseXml(xml, dc);

		}catch (IEWSException e){
			throw new IEWSException(e);

		}
		log.info("Finished to parse barcode: "+barcode+" to dc format");
	}
	
	private static void parseXml(String xml ,DublinCore dc)throws RepositoryException ,IEWSException, IOException, SQLException{
			
			DublinCore dcTemp =null;
			try {
				dcTemp = DublinCoreFactory.getInstance().createDocument(xml);
			} catch (DocumentException e) {
				log.error("Cannot parse cms xml");
				throw new IEWSException("cannot parse cms xml");
			}
			Document m_document = dcTemp.getDocument();
			org.dom4j.Element root = m_document.getRootElement();
			// iterate through child elements of root
	        for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
	        	org.dom4j.Element element = (org.dom4j.Element) i.next();
	        	setDcData(element, dc);	        		        	
	        }
	        dcRecord = dc;
	}
	
	private static void setDcData(org.dom4j.Element element, DublinCore dc)throws RepositoryException ,IEWSException {
		if (element == null)
			return;
		Map<String,Integer> allPrefix = PrefixMap();
		Map<String,Integer> allNameSpace = NameSpaceMap();
		String namespacePrefix =element.getNamespacePrefix();
		String namespaceURI	=element.getNamespaceURI();
		int NameSpace=10;
		String tag =element.getName();
		if(namespacePrefix!=""){
			if(allPrefix.get(namespacePrefix.toLowerCase())==null){
				return;
			}
			NameSpace=allPrefix.get(namespacePrefix.toLowerCase());
		}
		else if(namespaceURI!=""){
			if(allNameSpace.get(namespaceURI.toLowerCase())==null){
				return;
			}
			NameSpace=allNameSpace.get(namespaceURI.toLowerCase());
		}
		else{
			throw new IEWSException("cannot parse  xml");
		}
		//add the element and xsi:type attribute (if defined) to the dc
		if (element.attributes().size()==0){
		    dc.addElement(NameSpace, tag, element.getText());
		}else{
			dc.addElement(NameSpace, tag, element,
					element.getText(),false);
		}
	}
	/**
	 * getIdsfromAlmaApi - Get the mms_id ,holding_id,pid from Alma and initialize the variables.
	 * @param barcode - the current barcode.
	 * @return - return true/false is success/failed to read the ids.
	 */
	
	private static void getIdsfromAlmaApi(String barcode) throws IOException {
		String url = almaUrl + "/items?item_barcode="+barcode;	
		HttpURLConnection con = null;     	
        con = callAlmaApi(con,GET_METHOD,url,null);
        InputStream is = null;
    	BufferedReader reader = null;
    	String xmlResponse = "";
    	try {
    		xmlResponse = returnResponseContent(con,is,reader);
		    if(!xmlResponse.isEmpty()){
		    	if(con.getResponseCode() == OK_CODE){
		    		Item item = JAXB.unmarshal(new StringReader(xmlResponse), Item.class);
		    		mmsValue = item.getBibData().getMmsId();
		    		holdingValue = item.getHoldingData().getHoldingId();
		    		itemValue = item.getItemData().getPid();
		    	}else{
		    		log.error("Failed to get the ids from alma:");
		    		throw new IOException();
		    	}
		    }
		} catch (IOException e) {
			log.error("barcode id: "+barcode+" "+e.getMessage());
			throw e;
		}
	}

	private static void initParams() {
		
		almaUrl =  fileConfigProperties.getProperty("almaUrl");
		ApiKey = fileConfigProperties.getProperty("apiKey");
		digitizationDeptCode = fileConfigProperties.getProperty("digitizationDeptCode");
		sruUrl = fileConfigProperties.getProperty("sruUrl");
		rosettaMFId = fileConfigProperties.getProperty("rosettaMFId");
		rosettaProducerId = fileConfigProperties.getProperty("rosettaProducerId");
		rosettaPasswordAuthentication   = fileConfigProperties.getProperty("rosettaPasswordAuthentication");
		scannedFilesNFSLocation= fileConfigProperties.getProperty("scannedFilesNFSLocation");
		SIPNFSLocation = fileConfigProperties.getProperty("SIPNFSLocation");
		rosettaUrl = fileConfigProperties.getProperty("rosettaUrl");
		userName = fileConfigProperties.getProperty("userName");
		inst = fileConfigProperties.getProperty("inst");
		
	}
	
	
	/**
	 * callAlmaApi - Send the request to Alma using rest API .
	 * @param con
	 * @param method - POST/GET/DELETE
	 * @param url - url to alma with specific params
	 * @param payload
	 * @return returns the response object.
	 */
	private static HttpURLConnection callAlmaApi(HttpURLConnection con ,String method ,String url,String payload) throws IOException{
		URL obj = null;
        try {
            obj = new URL(url);
        } catch (MalformedURLException e2) {
            log.error(e2.getMessage());
        }      
        con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/xml;charset=UTF-8");
        con.setRequestProperty("Authorization","apikey "+ApiKey);
        con.setDoOutput(true);
        //set the payload for post request
        if(method.equals(POST_METHOD)){
        	DataOutputStream wr = new DataOutputStream(con.getOutputStream());
       	 	wr.writeBytes(payload);
            wr.flush();
            wr.close();
        }
        con.connect();
        return con;
	}

	/**
	 * returnRequestContent - return the response content
	 * @return - return the response content
	 */
	private static String returnResponseContent(HttpURLConnection con, InputStream is, BufferedReader reader) throws IOException{
		try{
		String xmlResponse = "";
		if(con.getResponseCode() == OK_CODE){
	        is = con.getInputStream();
	    }else{
	        is = con.getErrorStream();
	    }
		//get the response message
		reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String line = "";
	    while((line = reader.readLine()) != null) {
	    	xmlResponse += line;
        }	
		return xmlResponse;
		}catch (IOException e) {
			log.error(e.getMessage());
			throw new IOException(e);
		}finally{
			reader.close();
			is.close();
		}
	}

	
	/**
	 * Add the object identifier from the dnx.
	 * @param ieDnxDocumentHelper 
	 * @param type - object Identifier Type
	 * 
	 * @return
	 */
	private static void addOiFromDnx(DnxDocumentHelper ieDnxDocumentHelper, String  type,String value) throws DigitoolException{
		ieDnxDocumentHelper.new ObjectIdentifier(type, value);
	}
	
	private static Map<String,Integer> PrefixMap() {
		Map<String,Integer> allPids = new HashMap<String, Integer>();
		allPids.put("dc",10);
		allPids.put("dcterms",20);
		allPids.put("mods",30);
		allPids.put("marcrel",40);
		return allPids;
	}
	private static Map<String,Integer> NameSpaceMap() {
		Map<String,Integer> allPids = new HashMap<String, Integer>();
		allPids.put("http://purl.org/dc/elements/1.1/",10);
		allPids.put("http://purl.org/dc/terms/",20);
		allPids.put("http://www.loc.gov/mods/v3",30);
		allPids.put("http://www.loc.gov/marc/relators/",40);
		return allPids;
	}

	

}
