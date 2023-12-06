package CustomList;

import entidades.Cita;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CitasList<E extends Object> extends JList<E> {

    private final DefaultListModel model;
    private final Color selectedColor;
    private int hoverIndex = -1;

    public CitasList() {
        model = new DefaultListModel();
        selectedColor = new Color(204, 204, 204);
        setModel(model);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent me) {
                hoverIndex = -1;
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    Cita selectedItem = (Cita) getSelectedValue();

                    System.out.println(selectedItem.getMotivo());
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                checkMouse(me);
            }

            @Override
            public void mouseDragged(MouseEvent me) {
                checkMouse(me);
            }
        });
    }

    private void checkMouse(MouseEvent me) {
        Point p = me.getPoint();
        int index = locationToIndex(p);
        if (index != hoverIndex) {
            hoverIndex = index;
            repaint();
        }
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jList, Object o, int index, boolean selected, boolean focus) {
                CitasListItem item = new CitasListItem();
                item.setItem(o);
                if (index == hoverIndex) {
                    item.setBackground(selectedColor);
                }
                return item;
            }
        };
    }

    public void addItem(Cita item) {
        model.addElement(item);
    }

    public void removeAllItem() {
        model.removeAllElements();
    }
}