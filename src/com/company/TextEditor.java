package com.company;
import javax.swing.*; //for building graphical user interface
import java.awt.datatransfer.FlavorEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;// class in java swing its represent frame and have a object as frame
    JMenuBar menuBar; //class for create menubar
    JMenu file,edit;  //class for menu
    JMenuItem newfile,savefile,openfile; //class for dropdown menu for file
    JMenuItem cut,copy,past,selectall,close; //class for dropdown menu for edit
    JTextArea textArea; //for write text

    TextEditor(){ //create a constractor
        frame=new JFrame(); //initilization frame
        textArea=new JTextArea();//inilization text area
        menuBar=new JMenuBar(); //initilization menubar

        //initilizing menu
        file =new JMenu("File");
        edit=new JMenu("Edit");
        //initilizing menuitems for file
        newfile=new JMenuItem("New window");
        savefile=new JMenuItem("Save file");
        openfile=new JMenuItem("Open file");
        //initilizing menuitems for edit
        cut=new JMenuItem("cut");
        copy=new JMenuItem("copy");
        past=new JMenuItem("past");
        selectall=new JMenuItem("Select all");
        close=new JMenuItem("close");



        frame.setBounds(100,100,800,600);//set the panal
        frame.setVisible(true);  //for visible the panal
        frame.setJMenuBar(menuBar);//visible the menubar

        //add action listiner to file new item
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        //add action listene for edit
        cut.addActionListener(this);
        copy.addActionListener(this);
        past.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);



        frame.add(textArea); //to add text area

        //add the menu to menubar
        menuBar.add(file);
        menuBar.add(edit);

        //adding menuitem in file menu
        file.add(newfile);
        file.add(savefile);
        file.add(openfile);
        //adding menuitem in edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(past);
        edit.add(selectall);
        edit.add(close);

    }
   @Override
   public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
       if(actionEvent.getSource()==copy){
           textArea.copy();
       }
       if(actionEvent.getSource()==past){
           textArea.paste();
       }
       if(actionEvent.getSource()==selectall){
         textArea.selectAll();
       }
       if(actionEvent.getSource()==close){
           System.exit(0);
       }
       if(actionEvent.getSource()==newfile){
           TextEditor newWindow=new TextEditor();

       }
       if(actionEvent.getSource()==savefile){
           //initilize the filechosser
           JFileChooser fileChosser=new JFileChooser("c");
           //get choosen option from file chosser
           int chosserOption =fileChosser.showSaveDialog(null);
           //if choose option approve option
           if(chosserOption==JFileChooser.APPROVE_OPTION){
               //createba new file with directory's path
               File file=new File(fileChosser.getSelectedFile().getAbsolutePath()+".txt");
           try{
               BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file));
               textArea.write(bufferedWriter);
               bufferedWriter.close();

           }
           catch (Exception exception){
               exception.printStackTrace();
           }
           }

       }
       if(actionEvent.getSource()==openfile){
           JFileChooser filechosser =new JFileChooser("c");
           int chooseOption=filechosser.showOpenDialog(null);
           if(chooseOption==JFileChooser.APPROVE_OPTION){
               File file =filechosser.getSelectedFile();
               String filePath=file.getPath();
               try{
                   FileReader fileReader=new FileReader(filePath);
                   BufferedReader bufferedReader=new BufferedReader(fileReader);
                   String intermediate= "",output="";
                   while ((intermediate= bufferedReader.readLine())!=null){
                     output+=intermediate+"\n";
                   }
                   textArea.setText(output);


               }
               catch (Exception exception){
                   exception.printStackTrace();
               }
           }

       }

   }


    public static void main(String[] args) {
	TextEditor texteditor=new TextEditor();

    }
}
