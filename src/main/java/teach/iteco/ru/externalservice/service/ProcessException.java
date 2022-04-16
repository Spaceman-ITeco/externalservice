package teach.iteco.ru.externalservice.service;

public class ProcessException extends RuntimeException{
    private int id;

    public int getDigital() {
        return id;
    }
    public ProcessException (String message, int id)
    {
        super(message);
        this.id=id;
    }
}
