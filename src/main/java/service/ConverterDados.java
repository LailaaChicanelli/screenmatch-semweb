package service;

import tools.jackson.databind.ObjectMapper;

public class ConverterDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T obterDados(String json, Class<T> tClass) {
        return mapper.readValue(json, tClass);
    }
}
