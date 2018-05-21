import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Dentaku extends JFrame{

  JPanel contentPane = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JTextField result = new JTextField("");
  double stackedValue = 0.0;
	boolean isStacked = false;
	boolean afterCalc = false;
	String currentOp = "";

  public Dentaku(){
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(250,300));
    this.setTitle("sample");
    this.setContentPane(contentPane);

    contentPane.add(result, BorderLayout.NORTH);

		JPanel keyPanel = new JPanel();
		keyPanel.setLayout(new GridLayout(4, 4));
		contentPane.add(keyPanel, BorderLayout.CENTER);

		keyPanel.add(new NumberButton("7"), 0);
		keyPanel.add(new NumberButton("8"), 1);
		keyPanel.add(new NumberButton("9"), 2);
		keyPanel.add(new CalcButton("÷"), 3);
		keyPanel.add(new NumberButton("4"), 4);
		keyPanel.add(new NumberButton("5"), 5);
		keyPanel.add(new NumberButton("6"), 6);
		keyPanel.add(new CalcButton("×"), 7);
		keyPanel.add(new NumberButton("1"), 8);
		keyPanel.add(new NumberButton("2"), 9);
		keyPanel.add(new NumberButton("3"), 10);
		keyPanel.add(new CalcButton("－"), 11);
		keyPanel.add(new NumberButton("0"), 12);
		keyPanel.add(new NumberButton("."), 13);
		keyPanel.add(new CalcButton("＋"), 14);
		keyPanel.add(new CalcButton("＝"), 15);

		contentPane.add(new ClearButton(), BorderLayout.SOUTH);
		this.setVisible(true);
  }

  public void appendResult(String c){
    if(!afterCalc){
      result.setText(result.getText() + c);
    }else{
        result.setText(c);
        afterCalc = false;
    }
  }

  public class NumberButton extends JButton implements ActionListener{
    public NumberButton(String keyTop){
      super(keyTop);
      this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt){
      String keyNumber = this.getText();
      appendResult(keyNumber);
    }
  }

  public class CalcButton extends JButton implements ActionListener{
    public CalcButton(String op){
      super(op);
      this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ev){
      if(isStacked){
        double resultValue = (Double.valueOf(result.getText())).doubleValue();
        if(currentOp.equals("＋")){
          stackedValue += resultValue;
        }else if(currentOp.equals("－")){
          stackedValue -= resultValue;
        }else if(currentOp.equals("×")){
          stackedValue *= resultValue;
        }else if(currentOp.equals("÷")){
          stackedValue /= resultValue;
        }
        result.setText(String.valueOf(stackedValue));
      }

      currentOp = this.getText();
			stackedValue = (Double.valueOf(result.getText())).doubleValue();
			afterCalc = true;
			if (currentOp.equals("＝")){
				isStacked = false;
      }else{
				isStacked = true;
      }
    }
  }

  public class ClearButton extends JButton implements ActionListener {

		public ClearButton() {
			super("C");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent evt) {
			stackedValue = 0.0;
			afterCalc = false;
			isStacked = false;
			result.setText("");
		}
	}
}
