package Resources;

//Enum is special class in java which has collection of constants and methods
public enum APIResources {
    //methodlari tanimlarken araya virgul koyuyoruz. method tanimla bitince sona noktali virgul
    addPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");
    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {

        return resource;
    }
}
