package xmlmodels.nestedXML;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Arrays;

@JacksonXmlRootElement(localName = "employees")
public final class Employees {
    @JacksonXmlElementWrapper(localName = "employee", useWrapping = false)
    private Employee[] employee;

    public Employees() {
    }

    public Employees(Employee[] employee) {
        this.employee = employee;
    }

    public Employee[] getEmployee() {
        return employee;
    }

    public void setEmployee(Employee[] employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "employees=" + Arrays.toString(employee) +
                '}';
    }
}