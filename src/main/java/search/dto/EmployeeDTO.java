package search.dto;
import java.net.URL;
import java.util.Date;

import static search.entity.Employee.COLLECTION_NAME;


public class EmployeeDTO {

    private long pId;
    private String pName;
    private double pPrice;
    private String brand;
    private String  category;
    private int units;
    private URL pimage;
    private int punit;

    public static String getCollectionName() {
        return COLLECTION_NAME;
    }

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public double getpPrice() {
        return pPrice;
    }

    public void setpPrice(double pPrice) {
        this.pPrice = pPrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public URL getPimage() {
        return pimage;
    }

    public void setPimage(URL pimage) {
        this.pimage = pimage;
    }

    public int getPunit() {
        return punit;
    }

    public void setPunit(int punit) {
        this.punit = punit;
    }
}