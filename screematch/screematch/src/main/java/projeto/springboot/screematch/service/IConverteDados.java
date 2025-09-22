package projeto.springboot.screematch.service;

public interface IConverteDados {
    <T> T Obterdados(String json, Class<T> classe);
}
