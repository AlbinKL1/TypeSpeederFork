package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;

public class PatchTest {

    @Test
    public void testPatchClassExists() {
        try {
            Class.forName("se.ju23.typespeeder.Entitys.Patch");
        } catch (ClassNotFoundException e) {
            throw new AssertionError("Patch class should exist.", e);
        }
    }

    @Test
    public void testPatchProperties() {
        try {
            Class<?> someClass = Class.forName("se.ju23.typespeeder.Entitys.Patch");

            Field patchVersion = someClass.getDeclaredField("patchVersion");
            assertNotNull(patchVersion, "Field 'patchVersion' should exist in the Patch class.");
            assertTrue(patchVersion.getType().equals(String.class), "Field 'patchVersion' should be of type String.");

            Field realeaseDateTime = someClass.getDeclaredField("releaseDateTime");
            assertNotNull(realeaseDateTime, "Field 'releaseDateTime' should exist in Patch class.");

            assertTrue(realeaseDateTime.getType().equals(LocalDateTime.class), "Field 'releaseDateTime' should be of type LocalDateTime.");

            Object instance = someClass.getDeclaredConstructor().newInstance();

            LocalDateTime releaseDateTimeValue = LocalDateTime.now();
            realeaseDateTime.set(instance,releaseDateTimeValue);

            LocalDateTime dateTimeValue = (LocalDateTime) realeaseDateTime.get(instance);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTimeValue.format(formatter);
            assertEquals(releaseDateTimeValue.format(formatter), formattedDateTime, "'releaseDateTime' field should have format 'yyyy-MM-dd HH:mm:ss'.");

            Method getterMethod = someClass.getDeclaredMethod("getReleaseDateTime");
            assertNotNull(getterMethod, "Getter method for field 'releaseDateTime' should exist.");

        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException e) {
            fail("Error occurred while testing properties of Patch.", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
