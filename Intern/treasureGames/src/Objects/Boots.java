package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Boots extends Object {
    public Boots() {
        name = "boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}