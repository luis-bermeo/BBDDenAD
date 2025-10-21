public class Producto {
    private int id;
    private String nombre;
    private int precio;
    private int cantidad;

    public Producto(int id, String nombre, int precio,  int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString(){
        return "Producto --> Id: " + id + ", Nombre: "  + nombre + ", Precio: " + precio + ", Cantidad: " + cantidad;
    }
}
