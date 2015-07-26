package rogersilva.bora.models;

/**
 * Created by RogerSilva on 7/26/2015.
 */
public class Event {

    // region INSTANCE VARIABLES

    private long mId;
    private String mName;
    private String mDescription;

    // endregion

    // region CONSTRUCTORS

    public Event(long id, String name, String description) {

        setId(id);
        setName(name);
        setDescription(description);
    }

    // endregion

    // region GETTERS AND SETTERS

    public long getId() {

        return mId;
    }

    public void setId(long id) {

        this.mId = id;
    }

    public String getName() {

        return mName;
    }

    public void setName(String name) {

        this.mName = name;
    }

    public String getDescription() {

        return mDescription;
    }

    public void setDescription(String description) {

        this.mDescription = description;
    }

    // endregion

    @Override
    public boolean equals(Object obj) {

        if (obj == null)
            return false;

        if (obj == this)
            return true;

        if (obj instanceof Event) {

            Event otherEvent = (Event) obj;

            return (otherEvent.getId() == this.getId() &&
                    otherEvent.getName().equals(this.getName()) &&
                    otherEvent.getDescription().equals(this.getDescription()));
        }

        return false;
    }
}
