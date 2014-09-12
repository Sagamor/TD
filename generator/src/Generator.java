import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Created by Sagamor on 12/09/2014.
 */
public class Generator {
    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.combineSubdirectories = true;
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;
        TexturePacker.process(settings, "generator/assets", "core/assets/skin", "skin");
    }
}
