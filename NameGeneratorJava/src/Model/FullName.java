package Model;

public class FullName {
    private String name;
    private String lastName;

    public FullName() {
        name = "";
        lastName = "";
    }

    public FullName(String nombre, String apellidos) {
        name = nombre;
        lastName = apellidos;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FullName{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
