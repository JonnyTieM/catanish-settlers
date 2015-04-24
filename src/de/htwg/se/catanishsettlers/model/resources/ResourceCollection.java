package de.htwg.se.catanishsettlers.model.resources;

/**
 * Created by Stephan on 04.04.2015.
 */
public class ResourceCollection {
    private int brick;
    private int lumber;
    private int wool;
    private int grain;
    private int ore;

    public ResourceCollection() {
        this(0);    // initializes fields
    }

    public ResourceCollection(int initialAmount) {
        brick = initialAmount;
        lumber = initialAmount;
        wool = initialAmount;
        grain = initialAmount;
        ore = initialAmount;
    }

    public int getBrick() {
        return brick;
    }

    public int getLumber() {
        return lumber;
    }

    public int getWool() {
        return wool;
    }

    public int getGrain() {
        return grain;
    }

    public int getOre() {
        return ore;
    }

    private void add(EResource resource) {                       // logic to add a resource
        switch (resource) {
            case BRICK:
                brick++;
                break;
            case GRAIN:
                grain++;
                break;
            case LUMBER:
                lumber++;
                break;
            case ORE:
                ore++;
                break;
            case WOOL:
                wool++;
                break;
        }
    }

    private void subtract(EResource resource) {                  // logic to subtract a resource
        switch (resource) {
            case BRICK:
                brick--;
                break;
            case GRAIN:
                grain--;
                break;
            case LUMBER:
                lumber--;
                break;
            case ORE:
                ore--;
                break;
            case WOOL:
                wool--;
                break;
        }
    }

    private void add(ResourceCollection resources) {  // logic to add a collection
        this.brick += resources.getBrick();
        this.lumber += resources.getLumber();
        this.wool += resources.getWool();
        this.grain += resources.getGrain();
        this.ore += resources.getOre();
    }

    private void subtract(ResourceCollection resources) {   // logic to subtract a collection
        this.brick -= resources.getBrick();
        this.lumber -= resources.getLumber();
        this.wool -= resources.getWool();
        this.grain -= resources.getGrain();
        this.ore -= resources.getOre();
    }

    public ResourceCollection add(Resource... resources) {                  // add resource(s)
        for (Resource resource : resources) add(resource);
        return this;
    }

//    public ResourceCollection add(ResourceCollection... additions) {        // add collection(s)
//        for (ResourceCollection resources : additions) add(resources);
//        return this;
//    }
//
//    public ResourceCollection subtract(Resource... resources) {             // subtract resource(s)
//        for (Resource resource : resources) subtract(resource);
//        return this;
//    }
//
//    public ResourceCollection subtract(ResourceCollection... subtraction) {    // subtract collection(s)
//        for (ResourceCollection resources : subtraction) subtract(resources);
//        return this;
//    }
//
//    public boolean isPositive() {
//        return (brick.isPositive() && lumber.isPositive() && wool.isPositive()
//                && grain.isPositive() && ore.isPositive());
//    }

    /**
     * returns 0 if equal in all resources
     * returns 1 if this > other in all resources
     * returns -1 if any one resource of this < other (which for example means, a cost cannot be payed)
     */
    public int compareTo(ResourceCollection other) {
        if (this.brick == other.getBrick() &&
                this.lumber == other.getLumber() &&
                this.wool == other.getWool() &&
                this.grain == other.getGrain() &&
                this.ore == other.getOre()) {
            return 0;
        } else if (this.brick < other.getBrick() ||
                this.lumber < other.getLumber() ||
                this.wool < other.getWool() ||
                this.grain < other.getGrain() ||
                this.ore < other.getOre()) {
            return -1;
        } else return 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Brick: ");
        sb.append(brick);
        sb.append(System.lineSeparator());

        sb.append("Lumber: ");
        sb.append(lumber);
        sb.append(System.lineSeparator());

        sb.append("Wool: ");
        sb.append(wool);
        sb.append(System.lineSeparator());

        sb.append("Grain: ");
        sb.append(grain);
        sb.append(System.lineSeparator());

        sb.append("Ore: ");
        sb.append(ore);
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
