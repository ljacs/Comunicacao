/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacao;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Leonardo
 */


public class DragButton extends JButton {
    
    int ind;
    
    public DragButton(int _ind){
        super();
        ind = _ind;
    }
    
    BufferedImage rotate(BufferedImage src){
        System.out.println("sucesso");
        int w = src.getWidth();
        int h = src.getHeight();

        AffineTransform t = new AffineTransform();
        t.setToRotation(Math.PI/2, w / 2d, h / 2d);

        // vertices da peca
        Point[] points = {
            new Point(0, 0),
            new Point(w, 0),
            new Point(w, h),
            new Point(0, h)
        };

        // transforma no retangulo destino
        t.transform(points, 0, points, 0, 4);

        // bounding box do retangulo-destino
        Point min = new Point(points[0]);
        Point max = new Point(points[0]);
        for (int i = 1, n = points.length; i < n; i ++) {
            Point p = points[i];
            double pX = p.getX(), pY = p.getY();

            // atualiza min e max x
            if (pX < min.getX()) min.setLocation(pX, min.getY());
            if (pX > max.getX()) max.setLocation(pX, max.getY());

            // atualiza min e max y
            if (pY < min.getY()) min.setLocation(min.getX(), pY);
            if (pY > max.getY()) max.setLocation(max.getX(), pY);
        }

        // nova altura e nova largura
        w = (int) (max.getX() - min.getX());
        h = (int) (max.getY() - min.getY());

        // nova transformacao
        double tx = min.getX();
        double ty = min.getY();

        // aplica
        AffineTransform translation = new AffineTransform();
        translation.translate(-tx, -ty);
        t.preConcatenate(translation);

        AffineTransformOp op = new AffineTransformOp(t, null);
        BufferedImage dst = new BufferedImage(w, h, src.getType());
        op.filter(src, dst);

        return dst;
   }
    
    BufferedImage lerImagem (int peca, boolean small){
        try {
            return ImageIO.read(
                        new File("assets/mao" + 
                        ((peca<10)?("0"+peca):peca) + (small?("_small"):("")) + ".png"));
        } catch (IOException e){
            System.out.println("IOException!");
            return null;
        }
    }
    
     boolean valida(int x, int y){ // valida posição nova da peça
       int xR, yR, xL, yL;
       xR = (ClientWindow.indR<25)?ClientWindow.nextRightX[ClientWindow.indR]:-1000;
       yR = (ClientWindow.indR<25)?ClientWindow.nextRightY[ClientWindow.indR]:-1000;
       xL = (ClientWindow.indL<25)?ClientWindow.nextLeftX[ClientWindow.indL]:-1000;
       yL = (ClientWindow.indL<25)?ClientWindow.nextLeftY[ClientWindow.indL]:-1000;
       
       System.out.printf("(%d, %d - indR = %d) e (%d, %d - indL = %d). Vc botou em (%d, %d).\n", xR, yR,ClientWindow.indR, xL, yL,ClientWindow.indL, x, y);
       // Para gabi: (239,241) -> (231,203) [x - 8, y - 38]
       
       
       if (ClientWindow.start){
           System.out.println(this.getWidth() + " " + this.getHeight());
           System.out.println("start = true");
           if (ClientWindow.start){
               
               System.out.println(">>> " + Math.abs(x-231) + " e >>>" + Math.abs(y-203));
               
               if (Math.abs(x - 231) <= 265 && Math.abs(y - 203) <= 265){
                    // coloca no centro
                     this.setLocation(231, 203);
                     ClientWindow.start = false;
                     ClientWindow.fixada[ind] = true;
                     //System.out.printf("Fixando %d\n", ind);
                     return true;
               }
               
               System.out.println("Return false");
               return false;
           }
       } else {
           System.out.println((ClientWindow.EPS + ((ClientWindow.rotacionada[ind])?90:0)) + " e " + (ClientWindow.EPS + ((ClientWindow.rotacionada[ind])?20:0)));
           if (Math.abs(x - xR) <= (ClientWindow.EPS + ((ClientWindow.rotacionada[ind])?90:0))
                   && Math.abs(y - yR) <= (ClientWindow.EPS + ((ClientWindow.rotacionada[ind])?20:0))){
               // coloca a direita
               this.setLocation(xR, yR);
               ClientWindow.fixada[ind] = true;
               //System.out.printf("Fixando %d\n", ind);
               ++ClientWindow.indR;
               return true;
           } else if (Math.abs(x - xL) <= (ClientWindow.EPS + ((ClientWindow.rotacionada[ind])?90:0)) 
                   && Math.abs(y - yL) <= (ClientWindow.EPS + ((ClientWindow.rotacionada[ind])?20:0))){
               // coloca a esquerda
               this.setLocation(xL, yL);
               ClientWindow.fixada[ind] =true;
               //System.out.printf("Fixando %d\n", ind);
               ++ClientWindow.indL;
               return true;
           }
       }
       return false;
   } 
     
    
   public void MouseDragged(java.awt.event.MouseEvent evt) {                                   
        // TODO add your handling code here:
        //ystem.out.printf("Mouse dragged - %d %d", draggedAtX, draggedAtY);
        if (ClientWindow.fixada[ind]) return;
        this.setLocation(evt.getX() - ClientWindow.draggedAtX[ind] + this.getLocation().x,
                        evt.getY() - ClientWindow.draggedAtY[ind] + this.getLocation().y + ((ClientWindow.rotacionada[ind])?20:0));
        
        
        
        
        System.out.println(ind + " -> " + ClientWindow.indR + " " + ClientWindow.vire[ClientWindow.indR] + " " + ClientWindow.rotacionada[ind] + " " + ClientWindow.fixada[ind]);
        
        if (ClientWindow.indL != 0 &&
                ClientWindow.vire[ClientWindow.indL] &&
                Math.abs(this.getX() - ClientWindow.nextLeftX[ClientWindow.indL-1]) <= ClientWindow.EPS + 100
                && Math.abs(this.getY() - ClientWindow.nextLeftY[ClientWindow.indL-1]) <= ClientWindow.EPS+30
                && !ClientWindow.rotacionada[ind]){
            
            System.out.println("Entrou! (2)");
            BufferedImage rot = rotate(this.lerImagem(ClientWindow.mao[ind], true));
            this.setSize(100,60);
            ClientWindow.rotacionada[ind] = true;
            this.setIcon(new ImageIcon(rot));
            this.setLocation(this.getX(), this.getY()+20);
            return;
            
        }
        
        if (ClientWindow.indL != 0 &&
                ClientWindow.vire[ClientWindow.indL] &&
                !(Math.abs(this.getX() - ClientWindow.nextLeftX[ClientWindow.indL-1]) <= ClientWindow.EPS+100
                && Math.abs(this.getY() - ClientWindow.nextLeftY[ClientWindow.indL-1]) <= ClientWindow.EPS+30)
                && (Math.abs(this.getX() - ClientWindow.nextLeftX[ClientWindow.indL-1]) <= ClientWindow.EPS + 120
                && Math.abs(this.getY() - ClientWindow.nextLeftY[ClientWindow.indL-1]) <= ClientWindow.EPS+50)
                && ClientWindow.rotacionada[ind]){
            
            this.setSize(60, 100);
            ClientWindow.rotacionada[ind] = false;
            this.setIcon(new ImageIcon(this.lerImagem(ClientWindow.mao[ind],true)));
            return;
        }
        
        
        if (ClientWindow.indR != 0 && 
                ClientWindow.vire[ClientWindow.indR] && 
                Math.abs(this.getX() - ClientWindow.nextRightX[ClientWindow.indR-1]) <= ClientWindow.EPS+100 
                && Math.abs(this.getY() - ClientWindow.nextRightY[ClientWindow.indR-1]) <= ClientWindow.EPS+30
                && !ClientWindow.rotacionada[ind]){
            
            System.out.println("entrou! (1)");
            BufferedImage rot = rotate(this.lerImagem(ClientWindow.mao[ind], true));
            this.setSize(100, 60);
            ClientWindow.rotacionada[ind] = true;
            System.out.println("Mudando icone!!");
            this.setIcon(new ImageIcon(rot));
            this.setLocation(this.getX(), this.getY()+20);
            return;
            
        }
        
        if (ClientWindow.indR != 0 && 
                ClientWindow.vire[ClientWindow.indR] && 
                !(Math.abs(this.getX() - ClientWindow.nextRightX[ClientWindow.indR-1]) <= ClientWindow.EPS+100 
                && Math.abs(this.getY() - ClientWindow.nextRightY[ClientWindow.indR-1]) <= ClientWindow.EPS+30)
                && (Math.abs(this.getX() - ClientWindow.nextRightX[ClientWindow.indR-1]) <= ClientWindow.EPS+120 
                && Math.abs(this.getY() - ClientWindow.nextRightY[ClientWindow.indR-1]) <= ClientWindow.EPS+50)
                && ClientWindow.rotacionada[ind]){
            
            System.out.println("desvirando");
            this.setSize(60, 100);
            ClientWindow.rotacionada[ind] = false;
            this.setIcon(new ImageIcon(this.lerImagem(ClientWindow.mao[ind],true)));
            return;
        }
        
    }                                  

    public void MouseReleased(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
        if (ClientWindow.fixada[ind]) return;
        
        if (valida(evt.getX() - ClientWindow.draggedAtX[ind] + this.getLocation().x,
                evt.getY() - ClientWindow.draggedAtY[ind] + this.getLocation().y) == false){
            if (ClientWindow.rotacionada[ind]){
                ClientWindow.rotacionada[ind] = false;
                this.setSize(60, 100);
            }
           this.setLocation(ClientWindow.x0[ind], ClientWindow.y0[ind]);
           this.setIcon(new ImageIcon (lerImagem(ClientWindow.mao[ind], false)));
        }
    }                                   

    public void MousePressed(java.awt.event.MouseEvent evt) {                                   
        // TODO add your handling code here:
        if (ClientWindow.fixada[ind]) return;
        ClientWindow.draggedAtX[ind] = evt.getX();
        ClientWindow.draggedAtY[ind] = evt.getY();
        this.setIcon(new ImageIcon (lerImagem(ClientWindow.mao[ind], true)));
    } 
    
}
