package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Chest extends Object{
    public Chest(){
        name="chest";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
