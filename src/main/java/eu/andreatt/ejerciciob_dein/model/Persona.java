package eu.andreatt.ejerciciob_dein.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

import java.util.Objects;

public class Persona {

    private StringProperty nombre;
    private StringProperty apellido;
    private IntegerProperty edad;

    public Persona(String nombre, String apellido, int edad) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.edad = new SimpleIntegerProperty(edad);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public IntegerProperty edadProperty() {
        return edad;
    }

    public int getEdad() {
        return edad.get();
    }

    public void setEdad(int edad) {
        this.edad.set(edad);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Comprobación de referencia
        if (o == null || getClass() != o.getClass()) return false; // Comprobación de tipo
        Persona persona = (Persona) o;

        // Comparar los valores de las propiedades, no las propiedades en sí
        return Objects.equals(getNombre(), persona.getNombre()) &&
                Objects.equals(getApellido(), persona.getApellido()) &&
                getEdad() == persona.getEdad(); // Comparar la edad directamente
    }

    @Override
    public int hashCode() {
        // Utilizar los valores de las propiedades en hashCode
        return Objects.hash(getNombre(), getApellido(), getEdad());
    }

}

