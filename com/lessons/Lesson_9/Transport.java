package com.lessons.Lesson_9;

public abstract class Transport {
    protected float speed;
    private int weight;
    private String color;
    protected byte[] coordinate;


    protected class Engine {
        private boolean isReady;
        private float mileage;

        protected void setValues(boolean isReady, float mileage) {
            this.isReady = isReady;
            this.mileage = mileage;
        }

        public void info() {
            if (isReady)
                System.out.println("[Engine is ready! Mileage: " + this.mileage + " km.]");
            else
                System.out.println("[Engine is not ready! Mileage: " + this.mileage + " km.]");
        }
    }

    protected void printValues() {
        System.out.println("Speed: " + speed);
        System.out.println("Weight: " + weight);
        System.out.println("Color: " + color);
        System.out.print("Coordinate: " + coordinate[0] + ", " + coordinate[1] + ", " + coordinate[2] + "\n");
    }

    protected void setValues(float speed, int weight, String color, byte[] coordinate) {
        this.speed = speed;
        this.weight = weight;
        this.color = color;
        this.coordinate = coordinate;
    }

    public abstract void moveX(int xCoord);

    public abstract void moveY(int yCoord);

    public abstract void moveZ(int zCoord);

    public abstract void stopObject();

    public abstract void description();
}
