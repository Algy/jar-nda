// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) disassembler 
// Source File Name:   FormDebugPanel.java

package com.jgoodies.forms.debug;

import com.jgoodies.forms.layout.FormLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

// Referenced classes of package com.jgoodies.forms.debug:
//            FormDebugUtils

public class FormDebugPanel extends JPanel
{

    public FormDebugPanel()
    {
    //    0    0:aload_0         
    //    1    1:aconst_null     
    //    2    2:invokespecial   #127 <Method void FormDebugPanel(FormLayout)>
    //    3    5:return          
    }

    public FormDebugPanel(FormLayout layout)
    {
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:iconst_0        
    //    3    3:iconst_0        
    //    4    4:invokespecial   #128 <Method void FormDebugPanel(FormLayout, boolean, boolean)>
    //    5    7:return          
    }

    public FormDebugPanel(boolean paintInBackground, boolean paintDiagonals)
    {
    //    0    0:aload_0         
    //    1    1:aconst_null     
    //    2    2:iload_1         
    //    3    3:iload_2         
    //    4    4:invokespecial   #128 <Method void FormDebugPanel(FormLayout, boolean, boolean)>
    //    5    7:return          
    }

    public FormDebugPanel(FormLayout layout, boolean paintInBackground, boolean paintDiagonals)
    {
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:invokespecial   #143 <Method void JPanel(java.awt.LayoutManager)>
    //    3    5:aload_0         
    //    4    6:getstatic       #117 <Field boolean paintRowsDefault>
    //    5    9:putfield        #116 <Field boolean paintRows>
    //    6   12:aload_0         
    //    7   13:getstatic       #118 <Field Color DEFAULT_GRID_COLOR>
    //    8   16:putfield        #119 <Field Color gridColor>
    //    9   19:aload_0         
    //   10   20:iload_2         
    //   11   21:invokevirtual   #126 <Method void setPaintInBackground(boolean)>
    //   12   24:aload_0         
    //   13   25:iload_3         
    //   14   26:invokevirtual   #125 <Method void setPaintDiagonals(boolean)>
    //   15   29:aload_0         
    //   16   30:getstatic       #118 <Field Color DEFAULT_GRID_COLOR>
    //   17   33:invokevirtual   #129 <Method void setGridColor(Color)>
    //   18   36:return          
    }

    public void setPaintInBackground(boolean b)
    {
    //    0    0:aload_0         
    //    1    1:iload_1         
    //    2    2:putfield        #115 <Field boolean paintInBackground>
    //    3    5:return          
    }

    public void setPaintDiagonals(boolean b)
    {
    //    0    0:aload_0         
    //    1    1:iload_1         
    //    2    2:putfield        #114 <Field boolean paintDiagonals>
    //    3    5:return          
    }

    public void setPaintRows(boolean b)
    {
    //    0    0:aload_0         
    //    1    1:iload_1         
    //    2    2:putfield        #116 <Field boolean paintRows>
    //    3    5:return          
    }

    public void setGridColor(Color color)
    {
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:putfield        #119 <Field Color gridColor>
    //    3    5:return          
    }

    protected void paintComponent(Graphics g)
    {
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:invokespecial   #142 <Method void JPanel.paintComponent(Graphics)>
    //    3    5:aload_0         
    //    4    6:getfield        #115 <Field boolean paintInBackground>
    //    5    9:ifeq            17
    //    6   12:aload_0         
    //    7   13:aload_1         
    //    8   14:invokespecial   #130 <Method void paintGrid(Graphics)>
    //    9   17:return          
    }

    public void paint(Graphics g)
    {
    //    0    0:aload_0         
    //    1    1:aload_1         
    //    2    2:invokespecial   #141 <Method void JPanel.paint(Graphics)>
    //    3    5:aload_0         
    //    4    6:getfield        #115 <Field boolean paintInBackground>
    //    5    9:ifne            17
    //    6   12:aload_0         
    //    7   13:aload_1         
    //    8   14:invokespecial   #130 <Method void paintGrid(Graphics)>
    //    9   17:return          
    }

    private void paintGrid(Graphics g)
    {
    //    0    0:aload_0         
    //    1    1:invokevirtual   #131 <Method java.awt.LayoutManager getLayout()>
    //    2    4:instanceof      #68  <Class FormLayout>
    //    3    7:ifne            11
    //    4   10:return          
    //    5   11:aload_0         
    //    6   12:invokestatic    #132 <Method com.jgoodies.forms.layout.FormLayout$LayoutInfo FormDebugUtils.getLayoutInfo(java.awt.Container)>
    //    7   15:astore_2        
    //    8   16:aload_2         
    //    9   17:invokevirtual   #135 <Method int com.jgoodies.forms.layout.FormLayout$LayoutInfo.getX()>
    //   10   20:istore_3        
    //   11   21:aload_2         
    //   12   22:invokevirtual   #136 <Method int com.jgoodies.forms.layout.FormLayout$LayoutInfo.getY()>
    //   13   25:istore          4
    //   14   27:aload_2         
    //   15   28:invokevirtual   #134 <Method int com.jgoodies.forms.layout.FormLayout$LayoutInfo.getWidth()>
    //   16   31:istore          5
    //   17   33:aload_2         
    //   18   34:invokevirtual   #133 <Method int com.jgoodies.forms.layout.FormLayout$LayoutInfo.getHeight()>
    //   19   37:istore          6
    //   20   39:aload_1         
    //   21   40:aload_0         
    //   22   41:getfield        #119 <Field Color gridColor>
    //   23   44:invokevirtual   #139 <Method void Graphics.setColor(Color)>
    //   24   47:aload_2         
    //   25   48:getfield        #120 <Field int[] com.jgoodies.forms.layout.FormLayout$LayoutInfo.columnOrigins>
    //   26   51:arraylength     
    //   27   52:iconst_1        
    //   28   53:isub            
    //   29   54:istore          7
    //   30   56:iconst_0        
    //   31   57:istore          8
    //   32   59:iload           8
    //   33   61:iload           7
    //   34   63:icmpgt          171
    //   35   66:iload           8
    //   36   68:ifeq            78
    //   37   71:iload           8
    //   38   73:iload           7
    //   39   75:icmpne          82
    //   40   78:iconst_1        
    //   41   79:goto            83
    //   42   82:iconst_0        
    //   43   83:istore          9
    //   44   85:aload_2         
    //   45   86:getfield        #120 <Field int[] com.jgoodies.forms.layout.FormLayout$LayoutInfo.columnOrigins>
    //   46   89:iload           8
    //   47   91:iaload          
    //   48   92:istore          10
    //   49   94:iload           9
    //   50   96:ifeq            103
    //   51   99:iconst_0        
    //   52  100:goto            105
    //   53  103:iload           4
    //   54  105:istore          11
    //   55  107:iload           9
    //   56  109:ifeq            119
    //   57  112:aload_0         
    //   58  113:invokevirtual   #123 <Method int getHeight()>
    //   59  116:goto            124
    //   60  119:iload           4
    //   61  121:iload           6
    //   62  123:iadd            
    //   63  124:istore          12
    //   64  126:iload           11
    //   65  128:istore          13
    //   66  130:iload           13
    //   67  132:iload           12
    //   68  134:icmpge          165
    //   69  137:iconst_3        
    //   70  138:iload           12
    //   71  140:iload           13
    //   72  142:isub            
    //   73  143:invokestatic    #140 <Method int Math.min(int, int)>
    //   74  146:istore          14
    //   75  148:aload_1         
    //   76  149:iload           10
    //   77  151:iload           13
    //   78  153:iconst_1        
    //   79  154:iload           14
    //   80  156:invokevirtual   #138 <Method void Graphics.fillRect(int, int, int, int)>
    //   81  159:iinc            13  5
    //   82  162:goto            130
    //   83  165:iinc            8  1
    //   84  168:goto            59
    //   85  171:aload_2         
    //   86  172:getfield        #121 <Field int[] com.jgoodies.forms.layout.FormLayout$LayoutInfo.rowOrigins>
    //   87  175:arraylength     
    //   88  176:iconst_1        
    //   89  177:isub            
    //   90  178:istore          7
    //   91  180:iconst_0        
    //   92  181:istore          8
    //   93  183:iload           8
    //   94  185:iload           7
    //   95  187:icmpgt          305
    //   96  190:iload           8
    //   97  192:ifeq            202
    //   98  195:iload           8
    //   99  197:iload           7
    //  100  199:icmpne          206
    //  101  202:iconst_1        
    //  102  203:goto            207
    //  103  206:iconst_0        
    //  104  207:istore          9
    //  105  209:aload_2         
    //  106  210:getfield        #121 <Field int[] com.jgoodies.forms.layout.FormLayout$LayoutInfo.rowOrigins>
    //  107  213:iload           8
    //  108  215:iaload          
    //  109  216:istore          10
    //  110  218:iload           9
    //  111  220:ifeq            227
    //  112  223:iconst_0        
    //  113  224:goto            228
    //  114  227:iload_3         
    //  115  228:istore          11
    //  116  230:iload           9
    //  117  232:ifeq            242
    //  118  235:aload_0         
    //  119  236:invokevirtual   #124 <Method int getWidth()>
    //  120  239:goto            246
    //  121  242:iload_3         
    //  122  243:iload           5
    //  123  245:iadd            
    //  124  246:istore          12
    //  125  248:iload           9
    //  126  250:ifne            260
    //  127  253:aload_0         
    //  128  254:getfield        #116 <Field boolean paintRows>
    //  129  257:ifeq            299
    //  130  260:iload           11
    //  131  262:istore          13
    //  132  264:iload           13
    //  133  266:iload           12
    //  134  268:icmpge          299
    //  135  271:iconst_3        
    //  136  272:iload           12
    //  137  274:iload           13
    //  138  276:isub            
    //  139  277:invokestatic    #140 <Method int Math.min(int, int)>
    //  140  280:istore          14
    //  141  282:aload_1         
    //  142  283:iload           13
    //  143  285:iload           10
    //  144  287:iload           14
    //  145  289:iconst_1        
    //  146  290:invokevirtual   #138 <Method void Graphics.fillRect(int, int, int, int)>
    //  147  293:iinc            13  5
    //  148  296:goto            264
    //  149  299:iinc            8  1
    //  150  302:goto            183
    //  151  305:aload_0         
    //  152  306:getfield        #114 <Field boolean paintDiagonals>
    //  153  309:ifeq            344
    //  154  312:aload_1         
    //  155  313:iload_3         
    //  156  314:iload           4
    //  157  316:iload_3         
    //  158  317:iload           5
    //  159  319:iadd            
    //  160  320:iload           4
    //  161  322:iload           6
    //  162  324:iadd            
    //  163  325:invokevirtual   #137 <Method void Graphics.drawLine(int, int, int, int)>
    //  164  328:aload_1         
    //  165  329:iload_3         
    //  166  330:iload           4
    //  167  332:iload           6
    //  168  334:iadd            
    //  169  335:iload_3         
    //  170  336:iload           5
    //  171  338:iadd            
    //  172  339:iload           4
    //  173  341:invokevirtual   #137 <Method void Graphics.drawLine(int, int, int, int)>
    //  174  344:return          
    }

    static 
    {
    //    0    0:iconst_1        
    //    1    1:putstatic       #117 <Field boolean paintRowsDefault>
    //    2    4:getstatic       #122 <Field Color Color.red>
    //    3    7:putstatic       #118 <Field Color DEFAULT_GRID_COLOR>
    //    4   10:return          
    }

    public static boolean paintRowsDefault;
    private static final Color DEFAULT_GRID_COLOR;
    private boolean paintInBackground;
    private boolean paintDiagonals;
    private boolean paintRows;
    private Color gridColor;
}
