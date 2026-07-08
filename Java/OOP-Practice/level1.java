class Car {
    
    //atributos

    String model;
    String version;
    int year;
    
    //constructors
   Car(String model, int year, String version){
        this.model = model;
        this.year = year;
        this.version = version;
    }
    //métodos
    

    //main
    public static void main(String[] args){
        Car car = new Car("F-150", 2025, "Tremor");
        System.out.println(car.model + " " + car.year + " " + car.version);

    }

    
}