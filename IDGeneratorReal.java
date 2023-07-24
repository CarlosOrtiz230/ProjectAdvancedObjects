class IDGeneratorReal implements IDGenerator {

    private static int idCounter = 0;

    @Override
    public String generateID() {
        return "ID" + (++idCounter);
    }
}