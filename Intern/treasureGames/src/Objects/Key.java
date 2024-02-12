package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key extends Object{
    public Key(){
        name="key";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
