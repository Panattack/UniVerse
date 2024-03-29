package gr.aueb.softeng.team02.model;

public class Secretary extends User {
    /**
     * Constructor for the secretary
     *
     * @param id       id of the secretary as an integer
     * @param username username of the secretary as a string
     * @param password password of the secretary as a string
     * @param name     name of the secretary as a string
     * @param surname  surname of the secretary as a string
     */
    public Secretary(int id, String username, String password, String name, String surname) {
        super(id, username, password, name, surname);
    }

    /**
     * Default constructor
     */
    public Secretary() {
    }

    /**
     * Compare the secretary with a different object. If they have the same id then they are equal
     *
     * @param other A different object or maybe the same
     * @return true or false depending on the equality
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof Secretary)) {
            return false;
        }

        Secretary newOne = (Secretary) other;
        return this.getId() == newOne.getId();
    }

    /**
     * Get the hash code based on the id of the secretary
     *
     * @return the id of the secretary as an integer
     */
    @Override
    public int hashCode() {
        return this.getId();
    }
}
