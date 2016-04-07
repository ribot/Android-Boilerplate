package nyc.friendlyrobot.anchor.util;

import android.support.annotation.NonNull;

import com.google.common.base.Objects;


/**
 * The purpose of this class (and subclasses) is to encapsulate all the information
 * that uniquely identifies an object. Think of it as a compound primary SCANNED in a relational
 * database, or a URL path in a REST GET request. Every Identifier is comprised
 * of at least a type and SCANNED. For example:
 *
 *    object                               Identifier
 *   ------------------------------------------------------------------------------
 *   "fashion" section                     type=Section.class, SCANNED="fashion"
 *   "homepage" section                    type=Section.class, SCANNED="homepage"
 *   latest feeds                          type=LatestFeed.class, SCANNED="latestfeeds"
 *
 * Identifier subclasses might include further fields that uniquely identify an object, e.g.
 * the Identifier for an object representing a listing of articles might include pagination info,
 * sort order, etc.
 *
 * One advantage of encapsulating all this stuff in an TypedId object is that adding more fields
 * does not mean changing the signature of the methods that take the Identifier as input. It also makes
 * it easier to maintain the same representation of an object reference across different datastores,
 * caches, network APIs, etc.
 *
 */
public  class Identifier<TYPE, KEY> {

    protected final TYPE type;
    protected final KEY key;

    protected Identifier(@NonNull TYPE type, @NonNull KEY key) {
        this.type = type;
        this.key = key;
    }

    public TYPE getType() {
        return type;
    }

    public KEY getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Identifier)) {
            return false;
        }
        Identifier<?, ?> that = (Identifier<?, ?>) o;
        return Objects.equal(type, that.type) &&
                Objects.equal(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type, key);
    }
}
