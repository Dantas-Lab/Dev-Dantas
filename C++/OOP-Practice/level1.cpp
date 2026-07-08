#include <string>
#include <iostream>

class Car{

    public:
        std::string model;
        int year;
        std::string version;

        Car(std::string model, int year, std::string version){
            this->model = model;
            this->year = year;
            this->version = version;
        }
};

int main() {
Car car("F-150", 2025, "Tremor");
std::cout << car.model << " " << car.year << " " << car.version << std::endl;
}