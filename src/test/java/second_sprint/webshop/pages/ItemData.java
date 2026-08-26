package second_sprint.webshop.pages;

public class ItemData {
  private final String name;
  private final String unitPrice;
  private final String qty;

  public ItemData(String name, String unitPrice, String qty) {
    this.name = name;
    this.unitPrice = unitPrice;
    this.qty = qty;
  }

  public String getName() {
    return name;
  }

  public String getUnitPrice() {
    return unitPrice;
  }

  public String getQty() {
    return qty;
  }
}