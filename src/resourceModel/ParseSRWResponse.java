package resourceModel;

import gov.loc.zing.srw.DiagnosticsType;
import gov.loc.zing.srw.RecordType;
import gov.loc.zing.srw.RecordsType;
import gov.loc.zing.srw.SearchRetrieveResponseDocument;
import gov.loc.zing.srw.SearchRetrieveResponseType;
import gov.loc.zing.srw.diagnostic.DiagnosticType;

import java.util.ArrayList;
import java.util.List;

import org.purl.dc.elements.x11.ElementType;

import srw.schema.x1.dcSchema.DcDocument;
import srw.schema.x1.dcSchema.SrwDcType;
import submissionAppAlmaIntegration.Logger;

public class ParseSRWResponse {

	static Logger log= new Logger();
	
	private List<SRWRecord> resultsObjects = new ArrayList<SRWRecord>();
	int numberOfRecords = 0;
	int recordsRetrived = 0;

	public ParseSRWResponse(String input) throws EREException {
		parse(input);
	}

	private void parse(String in)throws EREException {
		try {
			SearchRetrieveResponseDocument retriveDoc =
				SearchRetrieveResponseDocument.Factory.parse(in);
			SearchRetrieveResponseType rootDoc = retriveDoc.getSearchRetrieveResponse();
			numberOfRecords = rootDoc.getNumberOfRecords().intValue();

			DiagnosticsType diagnostics = rootDoc.getDiagnostics();
			String message = "";
			if ( diagnostics != null ){
				DiagnosticType[] diagnosticArray = diagnostics.getDiagnosticArray();
				for (int d= 0; d < diagnosticArray.length; d++){
					DiagnosticType dtype = diagnosticArray[d];
					message += dtype.getMessage() + " - " + dtype.getDetails();
				}
			}

			if (numberOfRecords > 0 ){
				RecordsType records = rootDoc.getRecords();
				RecordType [] recordsArray = records.getRecordArray();
				for ( int i=0; i < recordsArray.length; i++){
					RecordType record = recordsArray[i];
					if (record.getRecordData()==null) {
						log.error("Failed to parse record #"+ (i+1) +" - record.getRecordData()==null");
						continue;
					}
					SRWRecord srwrecord = new SRWRecord();
					String xml = record.getRecordData().toString();
					srwrecord.setData(xml);
					DcDocument dcDoc = DcDocument.Factory.parse(xml);
					SrwDcType srwdc = dcDoc.getDc();
					setIdentifierData(srwrecord, srwdc.getIdentifierArray());
					resultsObjects.add(srwrecord);
				}
			} else if (message.length() > 0){
				throw new EREException(message);
			} else {
				throw new EREException("This term is unable to fetch a record. Please redefine search term.");
			}
		} catch (Exception xmlex){
			throw new EREException(xmlex.getMessage());
		}
	}



	public int getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public int getRecordsRetrived() {
		return recordsRetrived;
	}

	public void setRecordsRetrived(int recordsRetrived) {
		this.recordsRetrived = recordsRetrived;
	}

	private void setIdentifierData(SRWRecord srwrecord, ElementType[] data ) throws EREException{
		boolean identifierExists = false;
		if (data != null){
			for (int i=0; i <data.length; i++){
				ElementType elmt = data[i];
				String value = elmt.getStringValue();
					// special handling
					srwrecord.setRepositoryCode(value);
					srwrecord.setRepositoryId(value);
					identifierExists = true;
				}
			}
			if (!identifierExists) {
				throw new EREException("Identifier field is missing");
			}
		}
	


	public List<SRWRecord> getResultsObjects() {
		return resultsObjects;
	}

	public void setResultsObjects(List<SRWRecord> resultsObjects) {
		this.resultsObjects = resultsObjects;
	}

}
