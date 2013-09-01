/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacao;

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/*
 * Testando aqui
 */


/**
 *
 * @author Leonardo José de Andrade Costa Santos (ljacs)
 */

public class ClientWindow extends javax.swing.JFrame {
    
    static int[] mao, x0, y0, nextLeftX, nextRightX, nextLeftY, nextRightY;
    static boolean[] fixada, vire;
    static int indL, indR;
    static boolean start;
    static final int EPS = 20;
    static int[] draggedAtX, draggedAtY;
    static JLabel[] pecas, pecasL, pecasR;
    int indPecas;
    static boolean[] rotacionada;

    /**
     * Creates new form ClientWindow
     */
    
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
    
    BufferedImage rotate(BufferedImage src){
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
    
    void setaMao(){
        for (int i = 0; i<6; ++i)
            if (mao[i]/10 > mao[i]%10) mao[i] = (mao[i]%10)*10 + mao[i]/10;
        BufferedImage[] imagensPecas = new BufferedImage[6];
        for (int i = 0; i<6; ++i)
            imagensPecas[i] = lerImagem(mao[i], false);
        Peca1.setIcon(new ImageIcon(imagensPecas[0]));
        Peca2.setIcon(new ImageIcon(imagensPecas[1]));
        Peca3.setIcon(new ImageIcon(imagensPecas[2]));
        Peca4.setIcon(new ImageIcon(imagensPecas[3]));
        Peca5.setIcon(new ImageIcon(imagensPecas[4]));
        Peca6.setIcon(new ImageIcon(imagensPecas[5]));
        
    }
    
    void maoVis(boolean vis){
        Peca1.setVisible(vis);
        Peca2.setVisible(vis);
        Peca3.setVisible(vis);
        Peca4.setVisible(vis);
        Peca5.setVisible(vis);
        Peca6.setVisible(vis);
    }
    
    void refresh(){
        if (pecas[0] != null){
            tab0.setIcon(pecas[0].getIcon());
            tab0.setVisible(true);
        }
        if (pecasL[0] != null){
            tabl1.setIcon(pecasL[0].getIcon());
            tabl1.setVisible(true);
        }
        if (pecasL[1] != null){
            tabl2.setIcon(pecasL[1].getIcon());
            tabl2.setVisible(true);
        }
        if (pecasL[2] != null){
            tabl3.setIcon(pecasL[2].getIcon());
            tabl3.setVisible(true);
        }
        if (pecasL[3] != null){
            tabl4.setIcon(pecasL[3].getIcon());
            tabl4.setSize(100,60);
            tabl4.setVisible(true);
        }
        
        if (pecasL[4] != null){
            tabl5.setIcon(pecasL[4].getIcon());
            tabl5.setSize(100,60);
            tabl5.setVisible(true);
        }
        
        if (pecasL[5] != null){
            tabl6.setIcon(pecasL[5].getIcon());
            tabl6.setSize(100,60);
            tabl6.setVisible(true);
        }
        
        if (pecasL[6] != null){
            tabl7.setIcon(pecasL[6].getIcon());
            tabl7.setVisible(true);
        }
        
        if (pecasL[7] != null){
            tabl8.setIcon(pecasL[7].getIcon());
            tabl8.setVisible(true);
        }
        if (pecasL[8] != null){
            tabl9.setIcon(pecasL[8].getIcon());
            tabl9.setVisible(true);
        }
        if (pecasL[9] != null){
            tabl10.setIcon(pecasL[9].getIcon());
            tabl10.setVisible(true);
        }
        if (pecasL[10] != null){
            tabl11.setIcon(pecasL[10].getIcon());
            tabl11.setVisible(true);
        }
        if (pecasL[11] != null){
            tabl12.setIcon(pecasL[11].getIcon());
            tabl12.setVisible(true);
        }
        
        if (pecasL[12] != null){
            tabl13.setIcon(pecasL[12].getIcon());
            tabl13.setSize(100,60);
            tabl13.setVisible(true);
        }
        
        if (pecasL[13] != null){
            tabl14.setIcon(pecasL[13].getIcon());
            tabl14.setSize(100,60);
            tabl14.setVisible(true);
        }
        
        if (pecasL[14] != null){
            tabl15.setIcon(pecasL[14].getIcon());
            tabl15.setVisible(true);
        }
        if (pecasL[15] != null){
            tabl16.setIcon(pecasL[15].getIcon());
            tabl16.setVisible(true);
        }
        if (pecasL[16] != null){
            tabl17.setIcon(pecasL[16].getIcon());
            tabl17.setVisible(true);
        }
        if (pecasL[17] != null){
            tabl18.setIcon(pecasL[17].getIcon());
            tabl18.setVisible(true);
        }
        if (pecasL[18] != null){
            tabl19.setIcon(pecasL[18].getIcon());
            tabl19.setVisible(true);
        }
        if (pecasL[19] != null){
            tabl20.setIcon(pecasL[19].getIcon());
            tabl20.setVisible(true);
        }
        if (pecasL[20] != null){
            tabl21.setIcon(pecasL[20].getIcon());
            tabl21.setSize(100,60);
            tabl21.setVisible(true);
        }
        
        if (pecasL[21] != null){
            tabl22.setIcon(pecasL[21].getIcon());
            tabl22.setVisible(true);
        }
        
        if (pecasL[22] != null){
            tabl23.setIcon(pecasL[22].getIcon());
            tabl23.setVisible(true);
        }
        
        if (pecasL[23] != null){
            tabl24.setIcon(pecasL[23].getIcon());
            tabl24.setVisible(true);
        }
        
        if (pecasL[24] != null){
            tabl25.setIcon(pecasL[24].getIcon());
            tabl25.setVisible(true);
        }
        
        if (pecasR[0] != null){
            tabr1.setIcon(pecasR[0].getIcon());
            tabr1.setVisible(true);
        }
        if (pecasR[1] != null){
            tabr2.setIcon(pecasR[1].getIcon());
            tabr2.setVisible(true);
        }
        if (pecasR[2] != null){
            tabr3.setIcon(pecasR[2].getIcon());
            tabr3.setVisible(true);
        }
        
        if (pecasR[3] != null){
            tabr4.setIcon(pecasR[3].getIcon());
            tabr4.setSize(100,60);
            tabr4.setVisible(true);
        }
        
        if (pecasR[4] != null){
            tabr5.setIcon(pecasR[4].getIcon());
            tabr5.setSize(100,60);
            tabr5.setVisible(true);
        }
        
        if (pecasR[5] != null){
            tabr6.setIcon(pecasR[5].getIcon());
            tabr6.setSize(100,60);
            tabr6.setVisible(true);
        }
        if (pecasR[6] != null){
            tabr7.setIcon(pecasR[6].getIcon());
            tabr7.setVisible(true);
        }
        if (pecasR[7] != null){
            tabr8.setIcon(pecasR[7].getIcon());
            tabr8.setVisible(true);
        }
        if (pecasR[8] != null){
            tabr9.setIcon(pecasR[8].getIcon());
            tabr9.setVisible(true);
        }
        if (pecasR[9] != null){
            tabr10.setIcon(pecasR[9].getIcon());
            tabr10.setVisible(true);
        }
        if (pecasR[10] != null){
            tabr11.setIcon(pecasR[10].getIcon());
            tabr11.setVisible(true);
        }
        if (pecasR[11] != null){
            tabr12.setIcon(pecasR[11].getIcon());
            tabr12.setVisible(true);
        }
        if (pecasR[12] != null){
            tabr13.setIcon(pecasR[12].getIcon());
            tabr13.setSize(100,60);
            tabr13.setVisible(true);
        }
        
        if (pecasR[13] != null){
            tabr14.setIcon(pecasR[13].getIcon());
            tabr14.setSize(100,60);
            tabr14.setVisible(true);
        }
        
        if (pecasR[14] != null){
            tabr15.setIcon(pecasR[14].getIcon());
            tabr15.setVisible(true);
        }
        
        if (pecasR[15] != null){
            tabr16.setIcon(pecasR[15].getIcon());
            tabr16.setVisible(true);
        }
        if (pecasR[16] != null){
            tabr17.setIcon(pecasR[16].getIcon());
            tabr17.setVisible(true);
        }
        if (pecasR[17] != null){
            tabr18.setIcon(pecasR[17].getIcon());
            tabr18.setVisible(true);
        }
        if (pecasR[18] != null){
            tabr19.setIcon(pecasR[18].getIcon());
            tabr19.setVisible(true);
        }
        if (pecasR[19] != null){
            tabr20.setIcon(pecasR[19].getIcon());
            tabr20.setVisible(true);
        }
        if (pecasR[20] != null){
            tabr21.setIcon(pecasR[20].getIcon());
            tabr21.setSize(100,60);
            tabr21.setVisible(true);
        }
        if (pecasR[21] != null){
            tabr22.setIcon(pecasR[21].getIcon());
            tabr22.setVisible(true);
        }
        if (pecasR[22] != null){
            tabr23.setIcon(pecasR[22].getIcon());
            tabr23.setVisible(true);
        }
        if (pecasR[23] != null){
            tabr24.setIcon(pecasR[23].getIcon());
            tabr24.setVisible(true);
        }
        if (pecasR[24] != null){
            tabr25.setIcon(pecasR[24].getIcon());
            tabr25.setVisible(true);
        }
    }
    
    void addPeca(int peca, boolean left){
        System.out.println(peca + " " + left + " " + start);
        if (indR >= 25 && !left) return;
        if (indL >= 25 && left) return;
        if (peca/10 > peca%10) peca = (peca%10)*10 + peca/10;
        
        if (start){
           pecas[0] = new JLabel();
           BufferedImage pic = this.lerImagem(peca, true);
           //pecas[0].setSize(pic.getWidth(), pic.getHeight());
           pecas[0].setIcon(new ImageIcon(pic));
           start = false;
        } else if (left){
            pecasL[indL] = new JLabel();
            BufferedImage pic = this.lerImagem(peca, true);
            if (vire[indL]){
                pic = rotate(pic);
            }
            pecasL[indL].setIcon(new ImageIcon(pic));
            indL++;
        } else {
            pecasR[indR] = new JLabel();
            BufferedImage pic = this.lerImagem(peca, true);
            if (vire[indR]){
                pic = rotate(pic);
            }
            pecasR[indR].setIcon(new ImageIcon(pic));
            indR++;
        }
        
        this.refresh();
    }
    
    public ClientWindow() {
        mao = new int[]{35, 13, 14, 35, 66, 02};
        x0 = new int[]{560, 640, 720, 800, 880, 960};
        y0 = new int[]{170, 170, 170, 170, 170, 170};
        
        nextLeftX = new int[]{231, 231, 231, 162, 98, 34, 39, 39, 39 , 
            39 , 39 , 39 , 34 , 98 , 166, 166, 166, 166, 166, 166, 97, 102,
            102, 102, 102};
        
        nextLeftY = new int[]{139, 75 , 11 , 16 , 16, 16, 44, 108, 172, 
            236, 300, 364, 433, 433, 398, 334, 270, 206, 142, 78 , 83, 112,
            176, 240, 304};
        
        nextRightX = new int[]{231, 231, 231, 260, 324, 388, 423, 423, 423, 
            423, 423, 423, 389, 325, 296, 296, 296, 296, 296, 296, 325, 360,
            360, 360, 360};
        
        nextRightY = new int[]{267, 330, 393, 428, 428, 428, 359, 295, 231, 
            167, 103, 39 , 10 , 10 , 5  , 69 , 133, 197, 261, 325, 360, 291,
            227, 163, 99};
        
        draggedAtX = new int[7];
        draggedAtY = new int[7];
        pecas = new JLabel[1];
        pecasL = new JLabel[28];
        pecasR = new JLabel[28];
        for (int i = 0; i<1; ++i) pecas[i] = null;
        for (int i = 0; i<28; ++i) pecasL[i] = null;
        for (int i = 0; i<28; ++i) pecasR[i] = null;
        indPecas = 0;
        rotacionada = new boolean[]{false, false, false, false, false, false};
        
        vire = new boolean[27];
        for (int i = 0; i<27; ++i) vire[i] = false;
        vire[3] = vire[4] = vire[5] = true;
        vire[12] = vire[13] = vire[20] = true;
        
        fixada = new boolean[]{false, false, false, false, false, false};
        initComponents();
        tab0.setVisible(false);
        tabl1.setVisible(false);
        tabl2.setVisible(false);
        tabl3.setVisible(false);
        tabl4.setVisible(false);
        tabl5.setVisible(false);
        tabl6.setVisible(false);
        tabl7.setVisible(false);
        tabl8.setVisible(false);
        tabl9.setVisible(false);
        tabl10.setVisible(false);
        tabl11.setVisible(false);
        tabl12.setVisible(false);
        tabl13.setVisible(false);
        tabl14.setVisible(false);
        tabl15.setVisible(false);
        tabl16.setVisible(false);
        tabl17.setVisible(false);
        tabl18.setVisible(false);
        tabl19.setVisible(false);
        tabl20.setVisible(false);
        tabl21.setVisible(false);
        tabl22.setVisible(false);
        tabl23.setVisible(false);
        tabl24.setVisible(false);
        tabl25.setVisible(false);
        tabr1.setVisible(false);
        tabr2.setVisible(false);
        tabr3.setVisible(false);
        tabr4.setVisible(false);
        tabr5.setVisible(false);
        tabr6.setVisible(false);
        tabr7.setVisible(false);
        tabr8.setVisible(false);
        tabr9.setVisible(false);
        tabr10.setVisible(false);
        tabr11.setVisible(false);
        tabr12.setVisible(false);
        tabr13.setVisible(false);
        tabr14.setVisible(false);
        tabr15.setVisible(false);
        tabr16.setVisible(false);
        tabr17.setVisible(false);
        tabr18.setVisible(false);
        tabr19.setVisible(false);
        tabr20.setVisible(false);
        tabr21.setVisible(false);
        tabr22.setVisible(false);
        tabr23.setVisible(false);
        tabr24.setVisible(false);
        tabr25.setVisible(false);
        //background.setVisible(false);
        setaMao();
        maoVis(true);
        start = true;
        indL = indR = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Peca1 = new DragButton(0);
        Peca2 = new DragButton(1);
        Peca3 = new DragButton(2);
        Peca4 = new DragButton(3);
        Peca5 = new DragButton(4);
        Peca6 = new DragButton(5);
        Nome1 = new javax.swing.JLabel();
        Nome2 = new javax.swing.JLabel();
        Nome3 = new javax.swing.JLabel();
        Nome4 = new javax.swing.JLabel();
        Pecas1 = new javax.swing.JLabel();
        Pecas2 = new javax.swing.JLabel();
        Pecas3 = new javax.swing.JLabel();
        Pecas4 = new javax.swing.JLabel();
        pontos_dupla1 = new javax.swing.JLabel();
        pontos_dupla2 = new javax.swing.JLabel();
        pecaSelect = new javax.swing.JTextField();
        addLeft = new javax.swing.JButton();
        addRight = new javax.swing.JButton();
        tab0 = new DragButton(0);
        tabl1 = new DragButton(0);
        tabl2 = new DragButton(0);
        tabl3 = new DragButton(0);
        tabl4 = new DragButton(0);
        tabl5 = new DragButton(0);
        tabl6 = new DragButton(0);
        tabl7 = new DragButton(0);
        tabl8 = new DragButton(0);
        tabl9 = new DragButton(0);
        tabl10 = new DragButton(0);
        tabl11 = new DragButton(0);
        tabl12 = new DragButton(0);
        tabl13 = new DragButton(0);
        tabl14 = new DragButton(0);
        tabl15 = new DragButton(0);
        tabl16 = new DragButton(0);
        tabl17 = new DragButton(0);
        tabl18 = new DragButton(0);
        tabl19 = new DragButton(0);
        tabl20 = new DragButton(0);
        tabl21 = new DragButton(0);
        tabl22 = new DragButton(0);
        tabl23 = new DragButton(0);
        tabl24 = new DragButton(0);
        tabl25 = new DragButton(0);
        tabr1 = new DragButton(0);
        tabr2 = new DragButton(0);
        tabr3 = new DragButton(0);
        tabr4 = new DragButton(0);
        tabr5 = new DragButton(0);
        tabr6 = new DragButton(0);
        tabr7 = new DragButton(0);
        tabr8 = new DragButton(0);
        tabr9 = new DragButton(0);
        tabr10 = new DragButton(0);
        tabr11 = new DragButton(0);
        tabr12 = new DragButton(0);
        tabr13 = new DragButton(0);
        tabr14 = new DragButton(0);
        tabr15 = new DragButton(0);
        tabr16 = new DragButton(0);
        tabr17 = new DragButton(0);
        tabr18 = new DragButton(0);
        tabr19 = new DragButton(0);
        tabr20 = new DragButton(0);
        tabr21 = new DragButton(0);
        tabr22 = new DragButton(0);
        tabr23 = new DragButton(0);
        tabr24 = new DragButton(0);
        tabr25 = new DragButton(0);
        background = new javax.swing.JLabel();
        Foto1 = new javax.swing.JLabel();
        Foto2 = new javax.swing.JLabel();
        Foto3 = new javax.swing.JLabel();
        Foto4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1050, 525));
        getContentPane().setLayout(null);

        Peca1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00.png"))); // NOI18N
        Peca1.setBorderPainted(false);
        Peca1.setContentAreaFilled(false);
        Peca1.setFocusPainted(false);
        Peca1.setFocusTraversalKeysEnabled(false);
        Peca1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Peca1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Peca1MouseReleased(evt);
            }
        });
        Peca1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Peca1MouseDragged(evt);
            }
        });
        getContentPane().add(Peca1);
        Peca1.setBounds(560, 170, 60, 100);

        Peca2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00.png"))); // NOI18N
        Peca2.setBorderPainted(false);
        Peca2.setContentAreaFilled(false);
        Peca2.setFocusPainted(false);
        Peca2.setFocusTraversalKeysEnabled(false);
        Peca2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Peca2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Peca2MouseReleased(evt);
            }
        });
        Peca2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Peca2MouseDragged(evt);
            }
        });
        getContentPane().add(Peca2);
        Peca2.setBounds(640, 170, 60, 100);

        Peca3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00.png"))); // NOI18N
        Peca3.setBorderPainted(false);
        Peca3.setContentAreaFilled(false);
        Peca3.setFocusPainted(false);
        Peca3.setFocusTraversalKeysEnabled(false);
        Peca3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Peca3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Peca3MouseReleased(evt);
            }
        });
        Peca3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Peca3MouseDragged(evt);
            }
        });
        getContentPane().add(Peca3);
        Peca3.setBounds(720, 170, 60, 100);

        Peca4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00.png"))); // NOI18N
        Peca4.setBorderPainted(false);
        Peca4.setContentAreaFilled(false);
        Peca4.setFocusPainted(false);
        Peca4.setFocusTraversalKeysEnabled(false);
        Peca4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Peca4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Peca4MouseReleased(evt);
            }
        });
        Peca4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Peca4MouseDragged(evt);
            }
        });
        getContentPane().add(Peca4);
        Peca4.setBounds(800, 170, 60, 100);

        Peca5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00.png"))); // NOI18N
        Peca5.setBorderPainted(false);
        Peca5.setContentAreaFilled(false);
        Peca5.setFocusPainted(false);
        Peca5.setFocusTraversalKeysEnabled(false);
        Peca5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Peca5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Peca5MouseReleased(evt);
            }
        });
        Peca5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Peca5MouseDragged(evt);
            }
        });
        getContentPane().add(Peca5);
        Peca5.setBounds(880, 170, 60, 100);

        Peca6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00.png"))); // NOI18N
        Peca6.setBorderPainted(false);
        Peca6.setContentAreaFilled(false);
        Peca6.setFocusPainted(false);
        Peca6.setFocusTraversalKeysEnabled(false);
        Peca6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Peca6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Peca6MouseReleased(evt);
            }
        });
        Peca6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Peca6MouseDragged(evt);
            }
        });
        getContentPane().add(Peca6);
        Peca6.setBounds(960, 170, 60, 100);

        Nome1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 13)); // NOI18N
        Nome1.setText("Jogador 1");
        getContentPane().add(Nome1);
        Nome1.setBounds(610, 15, 80, 16);

        Nome2.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 13)); // NOI18N
        Nome2.setText("Jogador 2");
        getContentPane().add(Nome2);
        Nome2.setBounds(803, 15, 80, 16);

        Nome3.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 13)); // NOI18N
        Nome3.setText("Jogador 3");
        getContentPane().add(Nome3);
        Nome3.setBounds(610, 87, 80, 16);

        Nome4.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 13)); // NOI18N
        Nome4.setText("Jogador 4");
        getContentPane().add(Nome4);
        Nome4.setBounds(803, 87, 80, 16);

        Pecas1.setText("6 peças");
        getContentPane().add(Pecas1);
        Pecas1.setBounds(612, 40, 60, 16);

        Pecas2.setText("6 peças");
        getContentPane().add(Pecas2);
        Pecas2.setBounds(805, 40, 60, 16);

        Pecas3.setText("6 peças");
        getContentPane().add(Pecas3);
        Pecas3.setBounds(612, 112, 60, 16);

        Pecas4.setText("6 peças");
        getContentPane().add(Pecas4);
        Pecas4.setBounds(805, 112, 60, 16);

        pontos_dupla1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ponto_0.png"))); // NOI18N
        getContentPane().add(pontos_dupla1);
        pontos_dupla1.setBounds(938, 0, 50, 60);

        pontos_dupla2.setIcon(new javax.swing.ImageIcon("/Users/Leonardo/Documents/Comunicacao/assets/ponto_0.png")); // NOI18N
        getContentPane().add(pontos_dupla2);
        pontos_dupla2.setBounds(938, 75, 50, 60);

        pecaSelect.setText("12");
        getContentPane().add(pecaSelect);
        pecaSelect.setBounds(580, 430, 84, 28);

        addLeft.setText("Left");
        addLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addLeftMousePressed(evt);
            }
        });
        getContentPane().add(addLeft);
        addLeft.setBounds(690, 410, 75, 29);

        addRight.setText("Right");
        addRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addRightMousePressed(evt);
            }
        });
        getContentPane().add(addRight);
        addRight.setBounds(690, 450, 77, 29);

        tab0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tab0.setBorderPainted(false);
        tab0.setContentAreaFilled(false);
        tab0.setFocusPainted(false);
        tab0.setFocusTraversalKeysEnabled(false);
        getContentPane().add(tab0);
        tab0.setBounds(231, 203, 60, 100);

        tabl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl1.setBorderPainted(false);
        tabl1.setContentAreaFilled(false);
        tabl1.setFocusPainted(false);
        tabl1.setFocusTraversalKeysEnabled(false);
        getContentPane().add(tabl1);
        tabl1.setBounds(231, 139, 60, 100);

        tabl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl2.setBorderPainted(false);
        tabl2.setContentAreaFilled(false);
        tabl2.setFocusPainted(false);
        tabl2.setFocusTraversalKeysEnabled(false);
        getContentPane().add(tabl2);
        tabl2.setBounds(231, 75, 60, 100);

        tabl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl3.setBorderPainted(false);
        tabl3.setContentAreaFilled(false);
        tabl3.setFocusPainted(false);
        tabl3.setFocusTraversalKeysEnabled(false);
        tabl3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl3MouseReleased(evt);
            }
        });
        tabl3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl3MouseDragged(evt);
            }
        });
        getContentPane().add(tabl3);
        tabl3.setBounds(231, 11, 60, 100);

        tabl4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl4.setBorderPainted(false);
        tabl4.setContentAreaFilled(false);
        tabl4.setFocusPainted(false);
        tabl4.setFocusTraversalKeysEnabled(false);
        tabl4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl4MouseReleased(evt);
            }
        });
        tabl4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl4MouseDragged(evt);
            }
        });
        getContentPane().add(tabl4);
        tabl4.setBounds(162, 16, 60, 100);

        tabl5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl5.setBorderPainted(false);
        tabl5.setContentAreaFilled(false);
        tabl5.setFocusPainted(false);
        tabl5.setFocusTraversalKeysEnabled(false);
        tabl5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl5MouseReleased(evt);
            }
        });
        tabl5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl5MouseDragged(evt);
            }
        });
        getContentPane().add(tabl5);
        tabl5.setBounds(98, 16, 60, 100);

        tabl6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl6.setBorderPainted(false);
        tabl6.setContentAreaFilled(false);
        tabl6.setFocusPainted(false);
        tabl6.setFocusTraversalKeysEnabled(false);
        tabl6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl6MouseReleased(evt);
            }
        });
        tabl6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl6MouseDragged(evt);
            }
        });
        getContentPane().add(tabl6);
        tabl6.setBounds(34, 16, 60, 100);

        tabl7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl7.setBorderPainted(false);
        tabl7.setContentAreaFilled(false);
        tabl7.setFocusPainted(false);
        tabl7.setFocusTraversalKeysEnabled(false);
        tabl7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl7MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl7MouseReleased(evt);
            }
        });
        tabl7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl7MouseDragged(evt);
            }
        });
        getContentPane().add(tabl7);
        tabl7.setBounds(39, 44, 60, 100);

        tabl8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl8.setBorderPainted(false);
        tabl8.setContentAreaFilled(false);
        tabl8.setFocusPainted(false);
        tabl8.setFocusTraversalKeysEnabled(false);
        tabl8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl8MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl8MouseReleased(evt);
            }
        });
        tabl8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl8MouseDragged(evt);
            }
        });
        getContentPane().add(tabl8);
        tabl8.setBounds(39, 108, 60, 100);

        tabl9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl9.setBorderPainted(false);
        tabl9.setContentAreaFilled(false);
        tabl9.setFocusPainted(false);
        tabl9.setFocusTraversalKeysEnabled(false);
        tabl9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl9MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl9MouseReleased(evt);
            }
        });
        tabl9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl9MouseDragged(evt);
            }
        });
        getContentPane().add(tabl9);
        tabl9.setBounds(39, 172, 60, 100);

        tabl10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl10.setBorderPainted(false);
        tabl10.setContentAreaFilled(false);
        tabl10.setFocusPainted(false);
        tabl10.setFocusTraversalKeysEnabled(false);
        tabl10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl10MouseReleased(evt);
            }
        });
        tabl10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl10MouseDragged(evt);
            }
        });
        getContentPane().add(tabl10);
        tabl10.setBounds(39, 236, 60, 100);

        tabl11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl11.setBorderPainted(false);
        tabl11.setContentAreaFilled(false);
        tabl11.setFocusPainted(false);
        tabl11.setFocusTraversalKeysEnabled(false);
        tabl11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl11MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl11MouseReleased(evt);
            }
        });
        tabl11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl11MouseDragged(evt);
            }
        });
        getContentPane().add(tabl11);
        tabl11.setBounds(39, 300, 60, 100);

        tabl12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl12.setBorderPainted(false);
        tabl12.setContentAreaFilled(false);
        tabl12.setFocusPainted(false);
        tabl12.setFocusTraversalKeysEnabled(false);
        tabl12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl12MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl12MouseReleased(evt);
            }
        });
        tabl12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl12MouseDragged(evt);
            }
        });
        getContentPane().add(tabl12);
        tabl12.setBounds(39, 364, 60, 100);

        tabl13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl13.setBorderPainted(false);
        tabl13.setContentAreaFilled(false);
        tabl13.setFocusPainted(false);
        tabl13.setFocusTraversalKeysEnabled(false);
        tabl13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl13MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl13MouseReleased(evt);
            }
        });
        tabl13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl13MouseDragged(evt);
            }
        });
        getContentPane().add(tabl13);
        tabl13.setBounds(34, 433, 60, 100);

        tabl14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl14.setBorderPainted(false);
        tabl14.setContentAreaFilled(false);
        tabl14.setFocusPainted(false);
        tabl14.setFocusTraversalKeysEnabled(false);
        tabl14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl14MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl14MouseReleased(evt);
            }
        });
        tabl14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl14MouseDragged(evt);
            }
        });
        getContentPane().add(tabl14);
        tabl14.setBounds(98, 433, 60, 100);

        tabl15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl15.setBorderPainted(false);
        tabl15.setContentAreaFilled(false);
        tabl15.setFocusPainted(false);
        tabl15.setFocusTraversalKeysEnabled(false);
        tabl15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl15MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl15MouseReleased(evt);
            }
        });
        tabl15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl15MouseDragged(evt);
            }
        });
        getContentPane().add(tabl15);
        tabl15.setBounds(166, 398, 60, 100);

        tabl16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl16.setBorderPainted(false);
        tabl16.setContentAreaFilled(false);
        tabl16.setFocusPainted(false);
        tabl16.setFocusTraversalKeysEnabled(false);
        tabl16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl16MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl16MouseReleased(evt);
            }
        });
        tabl16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl16MouseDragged(evt);
            }
        });
        getContentPane().add(tabl16);
        tabl16.setBounds(166, 334, 60, 100);

        tabl17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl17.setBorderPainted(false);
        tabl17.setContentAreaFilled(false);
        tabl17.setFocusPainted(false);
        tabl17.setFocusTraversalKeysEnabled(false);
        tabl17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl17MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl17MouseReleased(evt);
            }
        });
        tabl17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl17MouseDragged(evt);
            }
        });
        getContentPane().add(tabl17);
        tabl17.setBounds(166, 270, 60, 100);

        tabl18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl18.setBorderPainted(false);
        tabl18.setContentAreaFilled(false);
        tabl18.setFocusPainted(false);
        tabl18.setFocusTraversalKeysEnabled(false);
        tabl18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl18MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl18MouseReleased(evt);
            }
        });
        tabl18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl18MouseDragged(evt);
            }
        });
        getContentPane().add(tabl18);
        tabl18.setBounds(166, 206, 60, 100);

        tabl19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl19.setBorderPainted(false);
        tabl19.setContentAreaFilled(false);
        tabl19.setFocusPainted(false);
        tabl19.setFocusTraversalKeysEnabled(false);
        tabl19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl19MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl19MouseReleased(evt);
            }
        });
        tabl19.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl19MouseDragged(evt);
            }
        });
        getContentPane().add(tabl19);
        tabl19.setBounds(166, 142, 60, 100);

        tabl20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl20.setBorderPainted(false);
        tabl20.setContentAreaFilled(false);
        tabl20.setFocusPainted(false);
        tabl20.setFocusTraversalKeysEnabled(false);
        tabl20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl20MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl20MouseReleased(evt);
            }
        });
        tabl20.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl20MouseDragged(evt);
            }
        });
        getContentPane().add(tabl20);
        tabl20.setBounds(166, 78, 60, 100);

        tabl21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl21.setBorderPainted(false);
        tabl21.setContentAreaFilled(false);
        tabl21.setFocusPainted(false);
        tabl21.setFocusTraversalKeysEnabled(false);
        tabl21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl21MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl21MouseReleased(evt);
            }
        });
        tabl21.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl21MouseDragged(evt);
            }
        });
        getContentPane().add(tabl21);
        tabl21.setBounds(97, 83, 60, 100);

        tabl22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl22.setBorderPainted(false);
        tabl22.setContentAreaFilled(false);
        tabl22.setFocusPainted(false);
        tabl22.setFocusTraversalKeysEnabled(false);
        tabl22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl22MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl22MouseReleased(evt);
            }
        });
        tabl22.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl22MouseDragged(evt);
            }
        });
        getContentPane().add(tabl22);
        tabl22.setBounds(102, 112, 60, 100);

        tabl23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl23.setBorderPainted(false);
        tabl23.setContentAreaFilled(false);
        tabl23.setFocusPainted(false);
        tabl23.setFocusTraversalKeysEnabled(false);
        tabl23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl23MouseReleased(evt);
            }
        });
        tabl23.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl23MouseDragged(evt);
            }
        });
        getContentPane().add(tabl23);
        tabl23.setBounds(102, 176, 60, 100);

        tabl24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl24.setBorderPainted(false);
        tabl24.setContentAreaFilled(false);
        tabl24.setFocusPainted(false);
        tabl24.setFocusTraversalKeysEnabled(false);
        tabl24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl24MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl24MouseReleased(evt);
            }
        });
        tabl24.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl24MouseDragged(evt);
            }
        });
        getContentPane().add(tabl24);
        tabl24.setBounds(102, 240, 60, 100);

        tabl25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabl25.setBorderPainted(false);
        tabl25.setContentAreaFilled(false);
        tabl25.setFocusPainted(false);
        tabl25.setFocusTraversalKeysEnabled(false);
        tabl25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabl25MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabl25MouseReleased(evt);
            }
        });
        tabl25.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabl25MouseDragged(evt);
            }
        });
        getContentPane().add(tabl25);
        tabl25.setBounds(102, 304, 60, 100);

        tabr1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr1.setBorderPainted(false);
        tabr1.setContentAreaFilled(false);
        tabr1.setFocusPainted(false);
        tabr1.setFocusTraversalKeysEnabled(false);
        tabr1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr1MouseReleased(evt);
            }
        });
        tabr1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr1MouseDragged(evt);
            }
        });
        getContentPane().add(tabr1);
        tabr1.setBounds(231, 267, 60, 100);

        tabr2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr2.setBorderPainted(false);
        tabr2.setContentAreaFilled(false);
        tabr2.setFocusPainted(false);
        tabr2.setFocusTraversalKeysEnabled(false);
        tabr2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr2MouseReleased(evt);
            }
        });
        tabr2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr2MouseDragged(evt);
            }
        });
        getContentPane().add(tabr2);
        tabr2.setBounds(231, 330, 60, 100);

        tabr3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr3.setBorderPainted(false);
        tabr3.setContentAreaFilled(false);
        tabr3.setFocusPainted(false);
        tabr3.setFocusTraversalKeysEnabled(false);
        tabr3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr3MouseReleased(evt);
            }
        });
        tabr3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr3MouseDragged(evt);
            }
        });
        getContentPane().add(tabr3);
        tabr3.setBounds(231, 393, 60, 100);

        tabr4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr4.setBorderPainted(false);
        tabr4.setContentAreaFilled(false);
        tabr4.setFocusPainted(false);
        tabr4.setFocusTraversalKeysEnabled(false);
        tabr4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr4MouseReleased(evt);
            }
        });
        tabr4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr4MouseDragged(evt);
            }
        });
        getContentPane().add(tabr4);
        tabr4.setBounds(260, 428, 60, 100);

        tabr5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr5.setBorderPainted(false);
        tabr5.setContentAreaFilled(false);
        tabr5.setFocusPainted(false);
        tabr5.setFocusTraversalKeysEnabled(false);
        tabr5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr5MouseReleased(evt);
            }
        });
        tabr5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr5MouseDragged(evt);
            }
        });
        getContentPane().add(tabr5);
        tabr5.setBounds(324, 428, 60, 100);

        tabr6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr6.setBorderPainted(false);
        tabr6.setContentAreaFilled(false);
        tabr6.setFocusPainted(false);
        tabr6.setFocusTraversalKeysEnabled(false);
        tabr6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr6MouseReleased(evt);
            }
        });
        tabr6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr6MouseDragged(evt);
            }
        });
        getContentPane().add(tabr6);
        tabr6.setBounds(388, 428, 60, 100);

        tabr7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr7.setBorderPainted(false);
        tabr7.setContentAreaFilled(false);
        tabr7.setFocusPainted(false);
        tabr7.setFocusTraversalKeysEnabled(false);
        tabr7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr7MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr7MouseReleased(evt);
            }
        });
        tabr7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr7MouseDragged(evt);
            }
        });
        getContentPane().add(tabr7);
        tabr7.setBounds(423, 359, 60, 100);

        tabr8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr8.setBorderPainted(false);
        tabr8.setContentAreaFilled(false);
        tabr8.setFocusPainted(false);
        tabr8.setFocusTraversalKeysEnabled(false);
        tabr8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr8MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr8MouseReleased(evt);
            }
        });
        tabr8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr8MouseDragged(evt);
            }
        });
        getContentPane().add(tabr8);
        tabr8.setBounds(423, 295, 60, 100);

        tabr9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr9.setBorderPainted(false);
        tabr9.setContentAreaFilled(false);
        tabr9.setFocusPainted(false);
        tabr9.setFocusTraversalKeysEnabled(false);
        tabr9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr9MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr9MouseReleased(evt);
            }
        });
        tabr9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr9MouseDragged(evt);
            }
        });
        getContentPane().add(tabr9);
        tabr9.setBounds(423, 231, 60, 100);

        tabr10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr10.setBorderPainted(false);
        tabr10.setContentAreaFilled(false);
        tabr10.setFocusPainted(false);
        tabr10.setFocusTraversalKeysEnabled(false);
        tabr10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr10MouseReleased(evt);
            }
        });
        tabr10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr10MouseDragged(evt);
            }
        });
        getContentPane().add(tabr10);
        tabr10.setBounds(423, 167, 60, 100);

        tabr11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr11.setBorderPainted(false);
        tabr11.setContentAreaFilled(false);
        tabr11.setFocusPainted(false);
        tabr11.setFocusTraversalKeysEnabled(false);
        tabr11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr11MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr11MouseReleased(evt);
            }
        });
        tabr11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr11MouseDragged(evt);
            }
        });
        getContentPane().add(tabr11);
        tabr11.setBounds(423, 103, 60, 100);

        tabr12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr12.setBorderPainted(false);
        tabr12.setContentAreaFilled(false);
        tabr12.setFocusPainted(false);
        tabr12.setFocusTraversalKeysEnabled(false);
        tabr12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr12MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr12MouseReleased(evt);
            }
        });
        tabr12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr12MouseDragged(evt);
            }
        });
        getContentPane().add(tabr12);
        tabr12.setBounds(423, 39, 60, 100);

        tabr13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr13.setBorderPainted(false);
        tabr13.setContentAreaFilled(false);
        tabr13.setFocusPainted(false);
        tabr13.setFocusTraversalKeysEnabled(false);
        tabr13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr13MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr13MouseReleased(evt);
            }
        });
        tabr13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr13MouseDragged(evt);
            }
        });
        getContentPane().add(tabr13);
        tabr13.setBounds(389, 10, 60, 100);

        tabr14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr14.setBorderPainted(false);
        tabr14.setContentAreaFilled(false);
        tabr14.setFocusPainted(false);
        tabr14.setFocusTraversalKeysEnabled(false);
        tabr14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr14MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr14MouseReleased(evt);
            }
        });
        tabr14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr14MouseDragged(evt);
            }
        });
        getContentPane().add(tabr14);
        tabr14.setBounds(325, 10, 60, 100);

        tabr15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr15.setBorderPainted(false);
        tabr15.setContentAreaFilled(false);
        tabr15.setFocusPainted(false);
        tabr15.setFocusTraversalKeysEnabled(false);
        tabr15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr15MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr15MouseReleased(evt);
            }
        });
        tabr15.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr15MouseDragged(evt);
            }
        });
        getContentPane().add(tabr15);
        tabr15.setBounds(296, 5, 60, 100);

        tabr16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr16.setBorderPainted(false);
        tabr16.setContentAreaFilled(false);
        tabr16.setFocusPainted(false);
        tabr16.setFocusTraversalKeysEnabled(false);
        tabr16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr16MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr16MouseReleased(evt);
            }
        });
        tabr16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr16MouseDragged(evt);
            }
        });
        getContentPane().add(tabr16);
        tabr16.setBounds(296, 69, 60, 100);

        tabr17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr17.setBorderPainted(false);
        tabr17.setContentAreaFilled(false);
        tabr17.setFocusPainted(false);
        tabr17.setFocusTraversalKeysEnabled(false);
        tabr17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr17MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr17MouseReleased(evt);
            }
        });
        tabr17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr17MouseDragged(evt);
            }
        });
        getContentPane().add(tabr17);
        tabr17.setBounds(296, 133, 60, 100);

        tabr18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr18.setBorderPainted(false);
        tabr18.setContentAreaFilled(false);
        tabr18.setFocusPainted(false);
        tabr18.setFocusTraversalKeysEnabled(false);
        tabr18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr18MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr18MouseReleased(evt);
            }
        });
        tabr18.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr18MouseDragged(evt);
            }
        });
        getContentPane().add(tabr18);
        tabr18.setBounds(296, 197, 60, 100);

        tabr19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr19.setBorderPainted(false);
        tabr19.setContentAreaFilled(false);
        tabr19.setFocusPainted(false);
        tabr19.setFocusTraversalKeysEnabled(false);
        tabr19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr19MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr19MouseReleased(evt);
            }
        });
        tabr19.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr19MouseDragged(evt);
            }
        });
        getContentPane().add(tabr19);
        tabr19.setBounds(296, 261, 60, 100);

        tabr20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr20.setBorderPainted(false);
        tabr20.setContentAreaFilled(false);
        tabr20.setFocusPainted(false);
        tabr20.setFocusTraversalKeysEnabled(false);
        tabr20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr20MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr20MouseReleased(evt);
            }
        });
        tabr20.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr20MouseDragged(evt);
            }
        });
        getContentPane().add(tabr20);
        tabr20.setBounds(296, 325, 60, 100);

        tabr21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr21.setBorderPainted(false);
        tabr21.setContentAreaFilled(false);
        tabr21.setFocusPainted(false);
        tabr21.setFocusTraversalKeysEnabled(false);
        tabr21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr21MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr21MouseReleased(evt);
            }
        });
        tabr21.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr21MouseDragged(evt);
            }
        });
        getContentPane().add(tabr21);
        tabr21.setBounds(325, 360, 60, 100);

        tabr22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr22.setBorderPainted(false);
        tabr22.setContentAreaFilled(false);
        tabr22.setFocusPainted(false);
        tabr22.setFocusTraversalKeysEnabled(false);
        tabr22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr22MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr22MouseReleased(evt);
            }
        });
        tabr22.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr22MouseDragged(evt);
            }
        });
        getContentPane().add(tabr22);
        tabr22.setBounds(360, 291, 60, 100);

        tabr23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr23.setBorderPainted(false);
        tabr23.setContentAreaFilled(false);
        tabr23.setFocusPainted(false);
        tabr23.setFocusTraversalKeysEnabled(false);
        tabr23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr23MouseReleased(evt);
            }
        });
        tabr23.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr23MouseDragged(evt);
            }
        });
        getContentPane().add(tabr23);
        tabr23.setBounds(360, 227, 60, 100);

        tabr24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr24.setBorderPainted(false);
        tabr24.setContentAreaFilled(false);
        tabr24.setFocusPainted(false);
        tabr24.setFocusTraversalKeysEnabled(false);
        tabr24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr24MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr24MouseReleased(evt);
            }
        });
        tabr24.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr24MouseDragged(evt);
            }
        });
        getContentPane().add(tabr24);
        tabr24.setBounds(360, 163, 60, 100);

        tabr25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mao00_small.png"))); // NOI18N
        tabr25.setBorderPainted(false);
        tabr25.setContentAreaFilled(false);
        tabr25.setFocusPainted(false);
        tabr25.setFocusTraversalKeysEnabled(false);
        tabr25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabr25MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabr25MouseReleased(evt);
            }
        });
        tabr25.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tabr25MouseDragged(evt);
            }
        });
        getContentPane().add(tabr25);
        tabr25.setBounds(360, 99, 60, 100);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Tela.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, -10, 1050, 530);

        Foto1.setIcon(new javax.swing.ImageIcon("/Users/Leonardo/Documents/Comunicacao/assets/user_avatar.png")); // NOI18N
        getContentPane().add(Foto1);
        Foto1.setBounds(542, 7, 60, 60);

        Foto2.setIcon(new javax.swing.ImageIcon("/Users/Leonardo/Documents/Comunicacao/assets/user_avatar.png")); // NOI18N
        getContentPane().add(Foto2);
        Foto2.setBounds(735, 7, 60, 60);

        Foto3.setIcon(new javax.swing.ImageIcon("/Users/Leonardo/Documents/Comunicacao/assets/user_avatar.png")); // NOI18N
        getContentPane().add(Foto3);
        Foto3.setBounds(542, 79, 60, 60);

        Foto4.setIcon(new javax.swing.ImageIcon("/Users/Leonardo/Documents/Comunicacao/assets/user_avatar.png")); // NOI18N
        getContentPane().add(Foto4);
        Foto4.setBounds(735, 79, 60, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    // Eventos:
    
    private void Peca1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca1MouseDragged
        // TODO add your handling code here:
        ((DragButton)this.Peca1).MouseDragged(evt);
    }//GEN-LAST:event_Peca1MouseDragged

    private void Peca1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca1MouseReleased
        // TODO add your handling code here:
        ((DragButton)this.Peca1).MouseReleased(evt);
    }//GEN-LAST:event_Peca1MouseReleased

    private void Peca1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca1MousePressed
        // TODO add your handling code here:
        ((DragButton)this.Peca1).MousePressed(evt);
    }//GEN-LAST:event_Peca1MousePressed

    private void Peca2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca2MouseDragged
        // TODO add your handling code here:
        ((DragButton)this.Peca2).MouseDragged(evt);
    }//GEN-LAST:event_Peca2MouseDragged

    private void Peca2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca2MouseReleased
       // TODO add your handling code here:
        ((DragButton)this.Peca2).MouseReleased(evt);
    }//GEN-LAST:event_Peca2MouseReleased

    private void Peca2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca2MousePressed
        // TODO add your handling code here:
        ((DragButton)this.Peca2).MousePressed(evt);
    }//GEN-LAST:event_Peca2MousePressed

    private void Peca3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca3MousePressed
        // TODO add your handling code here:
        ((DragButton)this.Peca3).MousePressed(evt);
    }//GEN-LAST:event_Peca3MousePressed

    private void Peca3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca3MouseReleased
        // TODO add your handling code here:
        ((DragButton)this.Peca3).MouseReleased(evt);
    }//GEN-LAST:event_Peca3MouseReleased

    private void Peca3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca3MouseDragged
        // TODO add your handling code here:
        ((DragButton)this.Peca3).MouseDragged(evt);
    }//GEN-LAST:event_Peca3MouseDragged

    private void Peca4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca4MousePressed
        // TODO add your handling code here:
        ((DragButton)this.Peca4).MousePressed(evt);
    }//GEN-LAST:event_Peca4MousePressed

    private void Peca4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca4MouseReleased
        // TODO add your handling code here:
        ((DragButton)this.Peca4).MouseReleased(evt);
    }//GEN-LAST:event_Peca4MouseReleased

    private void Peca4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca4MouseDragged
        // TODO add your handling code here:
        ((DragButton)this.Peca4).MouseDragged(evt);
    }//GEN-LAST:event_Peca4MouseDragged

    private void Peca5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca5MouseDragged
        // TODO add your handling code here:
        ((DragButton)this.Peca5).MouseDragged(evt);
    }//GEN-LAST:event_Peca5MouseDragged

    private void Peca6MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca6MouseDragged
        // TODO add your handling code here:
        ((DragButton)this.Peca6).MouseDragged(evt);
    }//GEN-LAST:event_Peca6MouseDragged

    private void Peca5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca5MousePressed
        // TODO add your handling code here:
        ((DragButton)this.Peca5).MousePressed(evt);
    }//GEN-LAST:event_Peca5MousePressed

    private void Peca6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca6MousePressed
        // TODO add your handling code here:
        ((DragButton)this.Peca6).MousePressed(evt);
    }//GEN-LAST:event_Peca6MousePressed

    private void Peca5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca5MouseReleased
        // TODO add your handling code here:
        ((DragButton)this.Peca5).MouseReleased(evt);
    }//GEN-LAST:event_Peca5MouseReleased

    private void Peca6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Peca6MouseReleased
        // TODO add your handling code here:
        ((DragButton)this.Peca6).MouseReleased(evt);
    }//GEN-LAST:event_Peca6MouseReleased

    private void addLeftMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addLeftMousePressed
        // TODO add your handling code here:
        addPeca(Integer.parseInt(this.pecaSelect.getText()), true);
    }//GEN-LAST:event_addLeftMousePressed

    private void addRightMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addRightMousePressed
        // TODO add your handling code here:
        addPeca(Integer.parseInt(this.pecaSelect.getText()), false);
    }//GEN-LAST:event_addRightMousePressed

    // Remover:
    
    private void tabl3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl3MousePressed

    private void tabl3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl3MouseReleased

    private void tabl3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl3MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl3MouseDragged

    private void tabr1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr1MousePressed

    private void tabr1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr1MouseReleased

    private void tabr1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr1MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr1MouseDragged

    private void tabr2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr2MousePressed

    private void tabr2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr2MouseReleased

    private void tabr2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr2MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr2MouseDragged

    private void tabr3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr3MousePressed

    private void tabr3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr3MouseReleased

    private void tabr3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr3MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr3MouseDragged

    private void tabl4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl4MousePressed

    private void tabl4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl4MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl4MouseReleased

    private void tabl4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl4MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl4MouseDragged

    private void tabr4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr4MousePressed

    private void tabr4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr4MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr4MouseReleased

    private void tabr4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr4MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr4MouseDragged

    private void tabl5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl5MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl5MousePressed

    private void tabl5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl5MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl5MouseReleased

    private void tabl5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl5MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl5MouseDragged

    private void tabl6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl6MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl6MousePressed

    private void tabl6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl6MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl6MouseReleased

    private void tabl6MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl6MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl6MouseDragged

    private void tabl7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl7MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl7MousePressed

    private void tabl7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl7MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl7MouseReleased

    private void tabl7MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl7MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl7MouseDragged

    private void tabl8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl8MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl8MousePressed

    private void tabl8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl8MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl8MouseReleased

    private void tabl8MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl8MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl8MouseDragged

    private void tabl9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl9MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl9MousePressed

    private void tabl9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl9MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl9MouseReleased

    private void tabl9MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl9MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl9MouseDragged

    private void tabl10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl10MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl10MousePressed

    private void tabl10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl10MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl10MouseReleased

    private void tabl10MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl10MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl10MouseDragged

    private void tabl11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl11MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl11MousePressed

    private void tabl11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl11MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl11MouseReleased

    private void tabl11MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl11MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl11MouseDragged

    private void tabl12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl12MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl12MousePressed

    private void tabl12MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl12MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl12MouseReleased

    private void tabl12MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl12MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl12MouseDragged

    private void tabl13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl13MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl13MousePressed

    private void tabl13MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl13MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl13MouseReleased

    private void tabl13MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl13MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl13MouseDragged

    private void tabl14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl14MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl14MousePressed

    private void tabl14MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl14MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl14MouseReleased

    private void tabl14MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl14MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl14MouseDragged

    private void tabl15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl15MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl15MousePressed

    private void tabl15MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl15MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl15MouseReleased

    private void tabl15MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl15MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl15MouseDragged

    private void tabl16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl16MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl16MousePressed

    private void tabl16MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl16MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl16MouseReleased

    private void tabl16MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl16MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl16MouseDragged

    private void tabl17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl17MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl17MousePressed

    private void tabl17MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl17MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl17MouseReleased

    private void tabl17MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl17MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl17MouseDragged

    private void tabl18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl18MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl18MousePressed

    private void tabl18MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl18MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl18MouseReleased

    private void tabl18MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl18MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl18MouseDragged

    private void tabl19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl19MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl19MousePressed

    private void tabl19MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl19MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl19MouseReleased

    private void tabl19MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl19MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl19MouseDragged

    private void tabl20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl20MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl20MousePressed

    private void tabl20MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl20MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl20MouseReleased

    private void tabl20MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl20MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl20MouseDragged

    private void tabl21MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl21MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl21MousePressed

    private void tabl21MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl21MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl21MouseReleased

    private void tabl21MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl21MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl21MouseDragged

    private void tabl22MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl22MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl22MousePressed

    private void tabl22MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl22MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl22MouseReleased

    private void tabl22MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl22MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl22MouseDragged

    private void tabl23MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl23MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl23MousePressed

    private void tabl23MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl23MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl23MouseReleased

    private void tabl23MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl23MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl23MouseDragged

    private void tabl24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl24MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl24MousePressed

    private void tabl24MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl24MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl24MouseReleased

    private void tabl24MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl24MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl24MouseDragged

    private void tabl25MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl25MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl25MousePressed

    private void tabl25MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl25MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl25MouseReleased

    private void tabl25MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabl25MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabl25MouseDragged

    private void tabr5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr5MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr5MousePressed

    private void tabr5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr5MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr5MouseReleased

    private void tabr5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr5MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr5MouseDragged

    private void tabr6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr6MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr6MousePressed

    private void tabr6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr6MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr6MouseReleased

    private void tabr6MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr6MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr6MouseDragged

    private void tabr7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr7MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr7MousePressed

    private void tabr7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr7MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr7MouseReleased

    private void tabr7MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr7MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr7MouseDragged

    private void tabr8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr8MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr8MousePressed

    private void tabr8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr8MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr8MouseReleased

    private void tabr8MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr8MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr8MouseDragged

    private void tabr9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr9MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr9MousePressed

    private void tabr9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr9MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr9MouseReleased

    private void tabr9MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr9MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr9MouseDragged

    private void tabr10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr10MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr10MousePressed

    private void tabr10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr10MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr10MouseReleased

    private void tabr10MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr10MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr10MouseDragged

    private void tabr11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr11MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr11MousePressed

    private void tabr11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr11MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr11MouseReleased

    private void tabr11MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr11MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr11MouseDragged

    private void tabr12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr12MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr12MousePressed

    private void tabr12MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr12MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr12MouseReleased

    private void tabr12MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr12MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr12MouseDragged

    private void tabr13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr13MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr13MousePressed

    private void tabr13MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr13MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr13MouseReleased

    private void tabr13MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr13MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr13MouseDragged

    private void tabr14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr14MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr14MousePressed

    private void tabr14MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr14MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr14MouseReleased

    private void tabr14MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr14MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr14MouseDragged

    private void tabr15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr15MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr15MousePressed

    private void tabr15MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr15MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr15MouseReleased

    private void tabr15MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr15MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr15MouseDragged

    private void tabr16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr16MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr16MousePressed

    private void tabr16MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr16MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr16MouseReleased

    private void tabr16MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr16MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr16MouseDragged

    private void tabr17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr17MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr17MousePressed

    private void tabr17MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr17MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr17MouseReleased

    private void tabr17MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr17MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr17MouseDragged

    private void tabr18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr18MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr18MousePressed

    private void tabr18MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr18MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr18MouseReleased

    private void tabr18MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr18MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr18MouseDragged

    private void tabr19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr19MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr19MousePressed

    private void tabr19MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr19MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr19MouseReleased

    private void tabr19MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr19MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr19MouseDragged

    private void tabr20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr20MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr20MousePressed

    private void tabr20MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr20MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr20MouseReleased

    private void tabr20MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr20MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr20MouseDragged

    private void tabr21MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr21MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr21MousePressed

    private void tabr21MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr21MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr21MouseReleased

    private void tabr21MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr21MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr21MouseDragged

    private void tabr22MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr22MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr22MousePressed

    private void tabr22MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr22MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr22MouseReleased

    private void tabr22MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr22MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr22MouseDragged

    private void tabr23MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr23MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr23MousePressed

    private void tabr23MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr23MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr23MouseReleased

    private void tabr23MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr23MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr23MouseDragged

    private void tabr24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr24MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr24MousePressed

    private void tabr24MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr24MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr24MouseReleased

    private void tabr24MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr24MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr24MouseDragged

    private void tabr25MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr25MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr25MousePressed

    private void tabr25MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr25MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr25MouseReleased

    private void tabr25MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabr25MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabr25MouseDragged


    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ClientWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Foto1;
    private javax.swing.JLabel Foto2;
    private javax.swing.JLabel Foto3;
    private javax.swing.JLabel Foto4;
    private javax.swing.JLabel Nome1;
    private javax.swing.JLabel Nome2;
    private javax.swing.JLabel Nome3;
    private javax.swing.JLabel Nome4;
    private javax.swing.JButton Peca1;
    private javax.swing.JButton Peca2;
    private javax.swing.JButton Peca3;
    private javax.swing.JButton Peca4;
    private javax.swing.JButton Peca5;
    private javax.swing.JButton Peca6;
    private javax.swing.JLabel Pecas1;
    private javax.swing.JLabel Pecas2;
    private javax.swing.JLabel Pecas3;
    private javax.swing.JLabel Pecas4;
    private javax.swing.JButton addLeft;
    private javax.swing.JButton addRight;
    private javax.swing.JLabel background;
    private javax.swing.JTextField pecaSelect;
    private javax.swing.JLabel pontos_dupla1;
    private javax.swing.JLabel pontos_dupla2;
    private javax.swing.JButton tab0;
    private javax.swing.JButton tabl1;
    private javax.swing.JButton tabl10;
    private javax.swing.JButton tabl11;
    private javax.swing.JButton tabl12;
    private javax.swing.JButton tabl13;
    private javax.swing.JButton tabl14;
    private javax.swing.JButton tabl15;
    private javax.swing.JButton tabl16;
    private javax.swing.JButton tabl17;
    private javax.swing.JButton tabl18;
    private javax.swing.JButton tabl19;
    private javax.swing.JButton tabl2;
    private javax.swing.JButton tabl20;
    private javax.swing.JButton tabl21;
    private javax.swing.JButton tabl22;
    private javax.swing.JButton tabl23;
    private javax.swing.JButton tabl24;
    private javax.swing.JButton tabl25;
    private javax.swing.JButton tabl3;
    private javax.swing.JButton tabl4;
    private javax.swing.JButton tabl5;
    private javax.swing.JButton tabl6;
    private javax.swing.JButton tabl7;
    private javax.swing.JButton tabl8;
    private javax.swing.JButton tabl9;
    private javax.swing.JButton tabr1;
    private javax.swing.JButton tabr10;
    private javax.swing.JButton tabr11;
    private javax.swing.JButton tabr12;
    private javax.swing.JButton tabr13;
    private javax.swing.JButton tabr14;
    private javax.swing.JButton tabr15;
    private javax.swing.JButton tabr16;
    private javax.swing.JButton tabr17;
    private javax.swing.JButton tabr18;
    private javax.swing.JButton tabr19;
    private javax.swing.JButton tabr2;
    private javax.swing.JButton tabr20;
    private javax.swing.JButton tabr21;
    private javax.swing.JButton tabr22;
    private javax.swing.JButton tabr23;
    private javax.swing.JButton tabr24;
    private javax.swing.JButton tabr25;
    private javax.swing.JButton tabr3;
    private javax.swing.JButton tabr4;
    private javax.swing.JButton tabr5;
    private javax.swing.JButton tabr6;
    private javax.swing.JButton tabr7;
    private javax.swing.JButton tabr8;
    private javax.swing.JButton tabr9;
    // End of variables declaration//GEN-END:variables
}
