package com.tucambio.tucambioapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

@Service
public class TipoCambioSoapService {

    private final String soapEndpoint = "https://banguat.gob.gt/variables/ws/TipoCambio.asmx";
    private final String soapAction = "http://www.banguat.gob.gt/variables/ws/TipoCambioRango";

    // Método principal que invoca el método getTipoCambioRango con fechas.
    public String obtenerTipoCambioRango(String fechaInit, String fechaFin) {
        return getTipoCambioRango(fechaInit, fechaFin);
    }

    // Método que construye y envía la solicitud SOAP
    public String getTipoCambioRango(String fechaInit, String fechaFin) {
        String soapRequest =
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                        + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                        + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
                        + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                        + "<soap:Body>"
                        + "<TipoCambioRango xmlns=\"http://www.banguat.gob.gt/variables/ws/\">"
                        + "<fechainit>" + fechaInit + "</fechainit>"
                        + "<fechafin>" + fechaFin + "</fechafin>"
                        + "</TipoCambioRango>"
                        + "</soap:Body>"
                        + "</soap:Envelope>";
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.add("SOAPAction", soapAction);

        HttpEntity<String> request = new HttpEntity<>(soapRequest, headers);

        try {
            String result = restTemplate.exchange(soapEndpoint, HttpMethod.POST, request, String.class).getBody();
            System.out.println(result);
            return result;
        } catch (Exception e) {
            // registrar el error o lanza una excepción más detallada
            return "Error al obtener el tipo de cambio rango: " + e.getMessage();
        }
    }
}