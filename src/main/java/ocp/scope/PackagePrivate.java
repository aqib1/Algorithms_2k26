package ocp.scope;

class PackagePrivate { // default access modifier make this only available inside a package

    public void printLightName(Light light) {
        IO.println(light.name);
    }
}
