package uk.co.ribot.androidboilerplate.test.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import uk.co.ribot.androidboilerplate.data.model.Name;
import uk.co.ribot.androidboilerplate.data.model.Profile;
import uk.co.ribot.androidboilerplate.data.model.Ribot;

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
public class TestDataFactory {

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    public static Ribot makeRibot(String uniqueSuffix) {
        return new Ribot(makeProfile(uniqueSuffix));
    }

    public static List<Ribot> makeListRibots(int number) {
        List<Ribot> ribots = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            ribots.add(makeRibot(String.valueOf(i)));
        }
        return ribots;
    }

    public static Profile makeProfile(String uniqueSuffix) {
        Profile profile = new Profile();
        profile.email = "email" + uniqueSuffix + "@ribot.co.uk";
        profile.name = makeName(uniqueSuffix);
        profile.dateOfBirth = new Date();
        profile.hexColor = "#0066FF";
        profile.avatar = "http://api.ribot.io/images/" + uniqueSuffix;
        profile.bio = randomUuid();
        return profile;
    }

    public static Name makeName(String uniqueSuffix) {
        Name name = new Name();
        name.first = "Name-" + uniqueSuffix;
        name.last = "Surname-" + uniqueSuffix;
        return name;
    }

}