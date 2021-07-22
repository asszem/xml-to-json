package com.ibm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import xmlmodels.nestedXML.Employees;
import xmlmodels.simpleXML.Flower;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

public class XmlToJson {
    public static void main(String[] args) {
        XmlToJson instance = new XmlToJson();
//        instance.simpleXMLToJson();
        instance.nestedXMLToJson();
    }

    /*
    * source: https://dzone.com/articles/parse-xml-to-java-objects-using-jackson
    * */
    private void nestedXMLToJson(){
        ObjectMapper objectMapper = new XmlMapper();
        // Reads from XML and converts to POJO
//                StringUtils.toEncodedString(Files.readAllBytes(Paths.get("/tmp/employees.xml")), StandardCharsets.UTF_8),
        Employees employees = null;
        try {
            employees = objectMapper.readValue(readXMLToString(getFilePath("employee.xml"), StandardCharsets.UTF_8), Employees.class);
            System.out.println("Employees object = \n" + employees);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employees);
            System.out.println("flower.xml converted to JSON = " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /*
     * source: https://www.baeldung.com/jackson-convert-xml-json
     * */
    private void simpleXMLToJson() {
        XmlMapper xmlMapper = new XmlMapper();
        String inputXML = readXMLToString(getFilePath("flower.xml"), Charset.defaultCharset());
        System.out.println("Input XML = \n" + inputXML);
        try {
            Flower flower = xmlMapper.readValue(inputXML, Flower.class);
            System.out.println("flower.xml converted with Jackson, Petals: " + flower.getPetals());
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(flower);
            System.out.println("flower.xml converted to JSON = " + json);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private String getFilePath(String fileName) {
        URL res = getClass().getClassLoader().getResource(fileName);
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    static String readXMLToString(String path, Charset encoding) {
        byte[] encoded = new byte[0];
        try {
            encoded = readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, encoding);
    }
}
