class Car:
    def __init__(self, model, year, version):
        self.model = model
        self.year = year
        self.version = version


car = Car("F-150", "2025", "Tremor")

print(car.model, car.year, car.version)