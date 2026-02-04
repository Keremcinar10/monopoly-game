package Lab2;

public class a {
    for (int i=0;i<10;i++) 
        {
            for (int j=0;j<5;j++) 
            {   if(i!=1 && j!=0 && i!=9 && j!=4 && gameBoard[i][j].equals("|....|"))
                {
                    System.out.print("      ");
                }
                else System.out.print(gameBoard[i][j]);
            }
            System.out.println("|");
        }
}int possiblePlayerLocations[][]= {{1,0},{1,1},{1,2},{1,3},{1,4},{3,4},{5,4},{7,4},{9,4},{9,3},{9,2},{9,1},{9,0},{7,0},{5,0},{3,0}};



for(Player aPlayer:this.players)
        {
            if(!aPlayer.IsPlayerEliminated())
            {
                int position = aPlayer.getPosition();
                int row = (position / 5) * 2;
                int col = position % 5;

                String currentCell=gameBoard[row+1][col] ;
                int dotPlace= currentCell.indexOf(".");
                if(dotPlace!=-1)
                {
                String newCell = currentCell.substring(0, dotPlace) + aPlayer.getSymbol() + currentCell.substring(dotPlace + 1);
                gameBoard[row + 1][col] = newCell;
                }
            }
        }




        String cells[]= {"A","B","C","D","E","F","G","H","I","J","K","L"};
        int index;
        if(pos<=3) 
        {
            index=pos-1;
        }
        else if(pos<=8)
        {
            index=pos-3;
        }
        else if(pos<=13) 
        {
            index=pos-5;
        }
        else
        {
            index=pos-7;
        }

        if(index>=0 && index<cells.length)
        {
            Property result= findTheProperty(cells[index]);
            return result;
        }
        return null;



        for(int i=0;i<3;i++)
        {
            properties.add(new Property(String.valueOf((char)('A' + (i))), 2, 1, new int[]{1,2,3,4,6}));
        }
        for(int j=0;j<3;j++)
        {
            properties.add(new Property(String.valueOf((char)('D' + (j))), 4, 1, new int[]{2,2,3,3,7}));
        }
        for(int a=0;a<3;a++)
        {
            properties.add(new Property(String.valueOf((char)('G' + (a))), 8, 3, new int[]{3,3,6,6,9}));
        }




        if(prp==null )
                { 
                    return "|"+cellName+"...|";
                }