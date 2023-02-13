
package newpackage;

import com.sun.xml.internal.ws.spi.db.PropertySetter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import static javafx.scene.paint.Color.color;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;




public class navbar extends JTabbedPane{
   public navbar(){
   setUI(new navbarUI());
   }
   
   
   
   
  public class navbarUI extends MetalTabbedPaneUI{
       
       public void setCurrentRectangle(Rectangle currentRectangle){
       this.currentRectangle = currentRectangle;
       repaint();
       }
       
       
        private  Animator animator;
        private  Rectangle currentRectangle;
        private  TimingTarget target;
        
        
        public navbarUI(){
         animator = new Animator(500);

        }
        
      public void installUI(Jcomponent jc) {
          super.installUI(jc);
          animator.setResolution(0);
          animator.setAcceleration(.5f);
          animator.setDeceleration(.5f);

    
        tabPane.addChangeListener(new changeListener(){
        
       /**
        *
        * @param ce
        */
       public void stateChanged(ChangeEvent ce){
            
            int selected = tabPane.getSelectedIndex();
            if( selected!= -1) {
            if(currentRectangle != null){
            if(animator.isRunning()){
            
            animator.stop();
            
            }
            animator.removeTarget(target);
            target = new PropertySetter(navbarUI.this,"currentRectangle", currentRectangle, getTabBounds(selected,calcRect )) {};
            animator.addTarget(target);
            animator.start();
                      
                    
            }   
            }
            }
        
        
        });
        
      
        
        }
       @Override
      protected Insets getTabInsets(int i, int i1){
          return new Insets(16,24,16,24);
      } 

       @Override
   protected void paintTabBorder(Graphics Graphcs, int tabPlacement,int tabIndex, int x, int y, int w, int h, boolean isSelected){
      Graphics2D g2 = (Graphics2D) Graphcs.create();
      
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setColor(new Color(3,155,216));
      
      
      Insets insets = getTabAreaInsets(tabPlacement);
      int width = tabPane.getWidth();
      int height = tabPane.getHeight();
      int tabheight = calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
      g2.drawLine(insets.left, tabheight, width-insets.right-1, tabheight);
      
      
      if(currentRectangle == null || ! animator.isRunning()){
        if(isSelected){
        setCurrentRectangle(new Rectangle(x,y,w,h));
        }
      
      }
     if(currentRectangle != null){
      g2.fillRect(currentRectangle.x, currentRectangle.y + currentRectangle.height -3, currentRectangle.width, 3);
      }
   g2.dispose();
   }
   
       @Override
   protected void paintContentBorder(Graphics Graphcs, int i, int i1){
   }
       @Override
   protected void paintFocusIndicator(Graphics Graphcs, int i,Rectangle[] rectungles, int i1, Rectangle Rectangle1, Rectangle Rectangle11, boolean bln){
   }
  }
   
}
   
   
   
