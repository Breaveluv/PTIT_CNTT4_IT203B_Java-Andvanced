package SS8.bai2.adapter;

public class ThermometerAdapter implements TemperatureSensor {
    private OldThermometer oldThermometer;

    public ThermometerAdapter(OldThermometer oldThermometer) {
        this.oldThermometer = oldThermometer;
    }

    @Override
    public double getTemperatureCelsius() {
        int tempF = oldThermometer.getTemperatureFahrenheit();
        return (tempF - 32) * 5.0 / 9.0;
    }
}
