import java.util.*;

class SnakesAndLadders {

    static class QueueEntry {
        int vertex; // Vertex number
        int distance; // distance of this vertex from source
    };

    static int MinimumDiceThrows(int board[], int N) {

        boolean[] visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            visited[i] = false;
        }

        ArrayList<QueueEntry> q = new ArrayList<>();

        visited[0] = true;
        QueueEntry s = new QueueEntry();
        s.distance = 0;
        s.vertex = 0;
        q.add(s);

        QueueEntry qe = new QueueEntry();

        while (!q.isEmpty()) {

            qe = q.get(0);// deqeue
            int vertex = qe.vertex;

            if (vertex == N - 1){
                break;
            }

            q.remove(0);
            for (int i = vertex + 1; i <= (vertex + 6) && i < N; ++i) {
                //get that cell and increment its distance by 1
            /*    if(visited[i]==true){
                    // when we will increment qe.distance it will get a wrong distance and finding that very element is very hard;
                    for(int j =0; j < q.size(); j++)
                        if(board[i] == q.get(j).vertex) {
                            QueueEntry cell = new QueueEntry();
                            cell.vertex = q.get(j).vertex;
                            cell.distance = q.get(j).distance+1;
                            q.set(j, cell);
                        }
                }*/



                if (visited[i] == false) {

                    QueueEntry cell = new QueueEntry();
                    cell.distance = (qe.distance + 1);
                    visited[i] = true;


                    if (board[i] != -1){//

                        cell.vertex = board[i];

                    }else{

                        cell.vertex = i;

                    }
                    q.add(cell);

                }
            }
        }

        return qe.distance;
    }


    public static void main(String[] args) {

        int N = 100; // number of cells


        int[] board = new int[N];
        int T,Ladders, Snakes;
        Scanner reader = new Scanner(System.in);
        T = reader.nextInt();
        for(int l=0; l<T; l++){

            for (int i = 0; i < N; i++)
                board[i] = -1;
            Ladders = reader.nextInt();
            for(int i=0; i< Ladders; i++){
                board[reader.nextInt()] = reader.nextInt();
            }

            Snakes = reader.nextInt();
            //I need to consider the cases when there are 6< consecutive snakeHeads
            int[] snakeHeads = new int[Snakes];
            int head;
            for(int i=0; i< Snakes; i++){
                head= reader.nextInt();
                board[head] = reader.nextInt();
                snakeHeads[i] = head;
            }

            //sort it up and check if there are 6 consecutive backtoBack heads
            Arrays.sort(snakeHeads);
            int count=0;
            for(int i=0;i<snakeHeads.length-1;i++)
                if(snakeHeads[i+1]-snakeHeads[i]==1)
                    ++count;
                else
                    count=0;
            if(count>=6)
                System.out.println(-1);
            else
                System.out.println(MinimumDiceThrows(board, N));
        }
    }

}
