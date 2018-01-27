/**
 * Created by zyz on 2017/4/21.
 * Caculator
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Caculator extends JFrame implements ActionListener{
    private JButton bnumber;
    private JButton boperator;
    private long fieldData;
    private double fieldDataDouble;
    private JTextArea result =new JTextArea("0",15,14);
    private int status=0;
    private int mark,mark2,mark3=0;
    private Double temp1,temp2;
    private  Caculator(){
        super("Caculator");
        Container c=getContentPane();
        c.setLayout(new BorderLayout());
        JPanel panelNorth=new JPanel();
        panelNorth.setLayout(new FlowLayout());
        result.setEditable(false);
        result.setBackground(Color.WHITE);
        result.setFont(new java.awt.Font("Calibri",Font.BOLD,30));
        //result.setHorizontalAlignment(JTextField.LEFT);
        panelNorth.add(result);

        //JPanel panelSouth=new JPanel();
        JPanel panelMiddle=new JPanel();
        panelMiddle.setLayout(new GridLayout(5,3));
        boperator=new JButton("AC");
        boperator.addActionListener(this);
        boperator.setFont(new java.awt.Font("Calibri",Font.BOLD,30));
        panelMiddle.add(boperator);//add AC operator

        boperator=new JButton("+/-");
        boperator.addActionListener(this);
        boperator.setFont(new java.awt.Font("Calibri",Font.BOLD,30));
        panelMiddle.add(boperator);// add reverse operator

        boperator= new JButton("%");
        boperator.addActionListener(this);
        boperator.setFont(new java.awt.Font("Calibri",Font.BOLD,30));
        panelMiddle.add(boperator);// add % operator

        //add number button
        String [] sNumber={"7","8","9","4","5","6","1","2","3","0"};
        for (int i=0;i<10;i++){
            bnumber=new JButton(sNumber[i]);
            bnumber.addActionListener(this);
            bnumber.setFont(new java.awt.Font("Calibri",Font.BOLD,30));
            bnumber.setBackground(Color.cyan);
            bnumber.setForeground(Color.BLACK);
            panelMiddle.add(bnumber);
        }

        boperator=new JButton(".");
        boperator.addActionListener(this);
        boperator.setFont(new java.awt.Font("Calibri",Font.BOLD,30));
        panelMiddle.add(boperator);

        boperator=new JButton("C");
        boperator.addActionListener(this);
        boperator.setFont(new java.awt.Font("Calibri",Font.BOLD,30));
        panelMiddle.add(boperator);
        //JPanel panelWest = new JPanel();

        JPanel panelEast= new JPanel();
        panelEast.setLayout(new GridLayout(5,1));
        String [] sOperator={"/","*","+","-","="};
        for (int i=0;i<sOperator.length;i++){
            boperator=new JButton(sOperator[i]);
            boperator.setFont(new java.awt.Font("Calibri",Font.BOLD,30));
            boperator.setBackground(Color.ORANGE);
            boperator.setForeground(Color.white);
            boperator.addActionListener(this);
            panelEast.add(boperator);
        }
        c.add(panelNorth,BorderLayout.NORTH);
        c.add(panelMiddle,BorderLayout.CENTER);
        c.add(panelEast,BorderLayout.EAST);
        panelNorth.setPreferredSize(new Dimension(100,100));
        panelEast.setPreferredSize(new Dimension(100,120));
    }

    public void actionPerformed(ActionEvent e){
        String s=e.getActionCommand();
        fieldDataDouble = Double.parseDouble(result.getText());
        if(Math.round(fieldDataDouble)-fieldDataDouble==0)//data is integer
            fieldData=Math.round(fieldDataDouble);
        if(s.equals("AC"))
            result.setText(""+0);
        else if(s.equals("+/-")){
            if(Math.round(fieldDataDouble)-fieldDataDouble==0)//data is integer which can be divided by 100
                result.setText(""+(0-fieldData));
            else
                result.setText(""+(0-fieldDataDouble));
        }
        else if(s.equals("%")){
            if(Math.round(fieldDataDouble/100)-fieldDataDouble/100==0)
                result.setText(""+fieldData/100);
            else
                result.setText(""+fieldDataDouble/100);
        }
        else if(s.equals("C")){
            if(Math.round(fieldDataDouble)-fieldDataDouble!=0){//data is not an integer
                for(int i=0;i<(result.getText().length()-result.getText().indexOf('.')-1);i++)
                    fieldDataDouble=fieldDataDouble*10;
                fieldDataDouble=fieldDataDouble-fieldDataDouble%10;
                for(int j=0;j<(result.getText().length()-result.getText().indexOf('.')-1);j++)
                    fieldDataDouble=fieldDataDouble/10;
                result.setText(""+fieldDataDouble);
            }
            else{
                result.setText(""+fieldData/10);
            }
        }
        else if(s.equals(".")){
            if(Math.round(fieldDataDouble)-fieldDataDouble!=0)
                ;
            else{
                result.setText(""+fieldData+".0");
                mark=1;
            }
        }
        else if(s.equals("/")){
            status=1;
            temp1=fieldDataDouble;
            mark2=1;
            result.setText("0");
        }else if(s.equals("*")){
            status=2;
            temp1=fieldDataDouble;
            mark2=1;
            result.setText("0");
        }else if(s.equals("-")){
            status=3;
            temp1=fieldDataDouble;
            mark2=1;
            result.setText("0");
        }else if(s.equals("+")){
            status=4;
            temp1=fieldDataDouble;
            mark2=1;
            result.setText("0");
        }else if(s.equals("=")){
            if(mark2==0){
                if(status==1){
                    result.setText(""+(fieldDataDouble/temp2));
                }
                else if (status==2) {
                    result.setText(""+(fieldDataDouble*temp2));
                }
                else if (status==3) {
                    result.setText(""+(fieldDataDouble-temp2));
                }
                else if (status==4) {
                    result.setText(""+(fieldDataDouble+temp2));
                }
            }
            else{
                if(status==1){
                    temp2=fieldDataDouble;
                    result.setText(""+(temp1/fieldDataDouble));
                    mark2=0;
                }
                else if (status==2) {
                    temp2=fieldDataDouble;
                    result.setText(""+(temp1*fieldDataDouble));
                    mark2=0;
                }
                else if (status==3) {
                    temp2=fieldDataDouble;
                    result.setText(""+(temp1-fieldDataDouble));
                    mark2=0;
                }
                else if (status==4) {
                    temp2=fieldDataDouble;
                    result.setText(""+(temp1+fieldDataDouble));
                    mark2=0;
                }
            }
        }
        else{
            if(fieldDataDouble>=0)
                ;
            else{
                mark3=1;
                fieldDataDouble=0-fieldDataDouble;
            }
            if(Math.round(fieldDataDouble)-fieldDataDouble!=0 && mark!=1){
                for(int i=0;i<(result.getText().length()-result.getText().indexOf('.'));i++)
                    fieldDataDouble=fieldDataDouble*10;
                fieldDataDouble=fieldDataDouble+Integer.parseInt(s);
                for(int j=0;j<(result.getText().length()-result.getText().indexOf('.'));j++)
                    fieldDataDouble=fieldDataDouble/10;
                if(mark3!=1)
                    result.setText(""+fieldDataDouble);
                else{
                    result.setText(""+(0-fieldDataDouble));
                    mark3=0;
                }
            }
            else{
                if(mark==1){
                    for(int i=0;i<(result.getText().length()-result.getText().indexOf('.'))-1;i++)
                        fieldDataDouble=fieldDataDouble*10;
                    fieldDataDouble=fieldDataDouble+Integer.parseInt(s);
                    for(int j=0;j<(result.getText().length()-result.getText().indexOf('.'))-1;j++)
                        fieldDataDouble=fieldDataDouble/10;
                    mark=0;
                    if(mark3!=1)
                        result.setText(""+fieldDataDouble);
                    else{
                        result.setText(""+(0-fieldDataDouble));
                        mark3=0;
                    }
                }
                else{
                    fieldData=fieldData*10 + Integer.parseInt(s);
                    if(mark3!=1)
                        result.setText(""+fieldData);
                    else{
                        result.setText(""+(0-fieldData));
                        mark3=0;
                    }
                }
            }
        }
    }

    public static void main(String args[]){
        Caculator app= new Caculator();
        app.setSize(360,360);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

}
