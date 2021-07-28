package soap;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT, 
        parameterStyle=javax.jws.soap.SOAPBinding.ParameterStyle.WRAPPED)
public interface MetroService {
	
	@WebMethod
	@WebResult(partName = "stationList")
	List<StationInfo> getAllStations();
	
	@WebMethod
	@WebResult(partName = "station")
	StationInfo getStationInfo(@WebParam(name="stationname") String stationname );

}
