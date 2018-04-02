package speeddev.info.skywars;


import org.bukkit.World; 
import org.bukkit.entity.Player;

import java.io.*;

public class Rollback {

    private static Rollback RollBack = new Rollback();
    private Rollback(){}
    public static Rollback getRollBack() {
        return RollBack;
    }

    public void rollback(World world) {
        for (Player player : world.getPlayers()) {
            player.teleport(Skywars.getInstance().getLobbyPoint());
        }

        Skywars.getInstance().getServer().unloadWorld(world, false);

        String originalName = world.getName().split("_")[0];

        rollback(originalName);
    }

    public void rollback(String worldName) {
        String rootDirectory = Skywars.getInstance().getServer().getWorldContainer().getAbsolutePath();

        File srcFolder = new File(rootDirectory + "/" + worldName);
        File destFolder = new File(rootDirectory + "/" + worldName + "_active");

        delete(destFolder);
        try {
            copyFolder(srcFolder, destFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(File delete){
        if (delete.isDirectory()) {
            String[] files = delete.list();

            if (files != null) {
                for (String file : files) {
                    File toDelete = new File(file);
                    delete(toDelete);
                }
            }
        } else {
            delete.delete();
        }
    }

    private void copyFolder(File src, File dest) throws IOException {
        if (src.isDirectory()) {

        
            if (!dest.exists()) {
                dest.mkdir();
            }

        
            String files[] = src.list();

            if (files != null) {
                for (String file : files) {
  
                    File srcFile = new File(src, file);
                    File destFile = new File(dest, file);

                    copyFolder(srcFile, destFile);
                }
            }
        } else {
     
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
        }
    }


}