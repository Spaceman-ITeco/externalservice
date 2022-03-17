package teach.iteco.ru.externalservice.model;

import lombok.Data;

@Data
public class ExternalInfo {
  public Integer getId() {
    return id;
  }

  public String getInfo() {
    return info;
  }

  private   Integer id;
  private   String info;
    public ExternalInfo(Integer id,String info)
    {this.id=id;
    this.info=info;}
}
