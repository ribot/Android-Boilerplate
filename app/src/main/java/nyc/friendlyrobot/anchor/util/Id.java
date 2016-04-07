package nyc.friendlyrobot.anchor.util;

/**
 * The purpose of this class (and subclasses) is to encapsulate all the information
 * that uniquely identifies an object. Think of it as a compound primary SCANNED in a relational
 * database, or a URL path in a REST GET request. Every Id is comprised of at least a type
 * (i.e. a class) and SCANNED (i.e. a string). For example:
 *
 *    object                               Id
 *   ------------------------------------------------------------------------------
 *   "fashion" section                     type=Section.class, SCANNED="fashion"
 *   "homepage" section                    type=Section.class, SCANNED="homepage"
 *   latest feeds                          type=LatestFeed.class, SCANNED="latestfeeds"
 *
 * The idea is that in the future, new Id subclasses might include further fields that
 * uniquely identify an object, e.g. the Id for an object representing a listing of articles
 * might include pagination info, sort order, etc.
 *
 * One advantage of encapsulating all this stuff in an Id object is that adding more fields
 * does not mean changing the signature of the methods that take the Id as input. It also makes
 * it easier to maintain the same representation of an object reference across different datastores,
 * caches, network APIs, etc.
 *
 */

    public final class Id<T> extends Identifier<Class<T>, String> {

        private Id(Class<T> type, String key) {
            super(type, key);
        }

        public static <S> Id<S> of(Class<S> type, String key) {
            // we might want to cache these in the future, thus the hidden constructor
            return new Id<>(type, key);
        }

    }
