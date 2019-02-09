package FifteenGamePackage;

import java.util.Random;

public class StateMatrix {
    private int N;
    private int NxN;
    private int[] state;
    private int spotIndex;
    private int spotRow;
    private int spotCol;

    public StateMatrix(int n) {
        N = n;
        NxN = n * n;
        state = new int[NxN];

        Reset();
    }

    public void Reset() {
        for (int i = 0; i < NxN; i++) {
            state[i] = i;
        }
        SetSpot(N - 1, N - 1);
        
        SetRandom();
    }

    private int Index(int i, int j) {
        return i * N + j;
    }

    public int getState(int index) {
        return state[index];
    }

    private void Swap(int i, int j) {
        int t = state[i];
        state[i] = state[j];
        state[j] = t;
    }

    private void SetSpot(int i, int j) {
        spotRow = i;
        spotCol = j;
        spotIndex = Index(i, j);
    }

    public void MoveSpotLeft() {
        if (spotCol != 0) {
            Swap(spotIndex, Index(spotRow, spotCol - 1));
            SetSpot(spotRow, spotCol - 1);
        }
    }

    public void MoveSpotRight() {
        if (spotCol != N - 1) {
            Swap(spotIndex, Index(spotRow, spotCol + 1));
            SetSpot(spotRow, spotCol + 1);
        }
    }

    public void MoveSpotUp() {
        if (spotRow != 0) {
            Swap(spotIndex, Index(spotRow - 1, spotCol));
            SetSpot(spotRow - 1, spotCol);
        }
    }

    public void MoveSpotDown() {
        if (spotRow != N - 1) {
            Swap(spotIndex, Index(spotRow + 1, spotCol));
            SetSpot(spotRow + 1, spotCol);
        }
    }

    private boolean SquareIsSpotsLeft(int i, int j) {
        return i == spotRow && j == spotCol - 1;
    }

    private boolean SquareIsSpotsRigth(int i, int j) {
        return i == spotRow && j == spotCol + 1;
    }

    private boolean SquareIsSpotsUp(int i, int j) {
        return i == spotRow - 1 && j == spotCol;
    }

    private boolean SquareIsSpotsDown(int i, int j) {
        return i == spotRow + 1 && j == spotCol;
    }

    public void MoveSquare(int index) {
        int i = (index - index % N) / N;
        int j = index % N;
        
        if (SquareIsSpotsLeft(i, j)) {
            MoveSpotLeft();
        } else 
        if (SquareIsSpotsRigth(i, j)) {
            MoveSpotRight();
        } else 
        if (SquareIsSpotsUp(i, j)) {
            MoveSpotUp();
        } else 
        if (SquareIsSpotsDown(i, j)) {
            MoveSpotDown();
        }            
    }

    private void SetRandom() {
        int steps = 100;
        Random random = new Random();

        for (int i = 0; i < steps; i++) {
            int rand = random.nextInt(4);
            switch(rand) {
                case 0: 
                    MoveSpotLeft();
                    break;
                case 1:
                    MoveSpotRight();
                    break;
                case 2:
                    MoveSpotUp();
                    break;
                case 3:
                    MoveSpotDown();
                    break;
                default:
                    break;
            }
        }
    } 

    public boolean GameIsFinished() {
        for (int i = 0; i < NxN; i++) {
            if (state[i] != i) {
                return false;
            }
        }

        return true;
    }
}
