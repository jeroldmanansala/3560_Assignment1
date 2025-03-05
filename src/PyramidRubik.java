import java.util.Arrays;

enum Color {Red, Green, Blue, Yellow}

class Face{

    private Color[] tiles;

    public Face(Color color) {
        tiles = new Color[9];
        Arrays.fill(tiles, color);

    }

    public Color[] getTiles() {
        return tiles;
    }

    public void setTile(int index, Color color) {
        tiles[index] = color;
    }

    public Color getFaceColor() {
        return tiles[0];
    }

    public boolean isValid() {
        return tiles.length == 9;
    }


}

public class PyramidRubik {

    private Face[] faces;
    private int[][] adjacentFaces = {
        {1, 2, 3}, // Face 0 is adjacent to faces 1, 2, 3
        {0, 2, 3}, // Face 1 is adjacent to faces 0, 2, 3
        {0, 1, 3}, // Face 2 is adjacent to faces 0, 1, 3
        {0, 1, 2}  // Face 3 is adjacent to faces 0, 1, 2
    };

    private int[][] edgeIndices = {
        {2, 5, 7},  // edge index for each face, 3 edges 
        {2, 5, 7},  
        {2, 5, 7},  
        {2, 5, 7}   
    };

    public PyramidRubik() {
        faces = new Face[4];
        initializeFaces();
        initializeEdges();
    }

    public Face[] getFaces() {
        return faces;
    }

    private void initializeFaces() {
        // Init each face with a color
        faces[0] = new Face(Color.Red);
        faces[1] = new Face(Color.Green);
        faces[2] = new Face(Color.Blue);
        faces[3] = new Face(Color.Yellow);

    }
    
    private void initializeEdges() {
        for (int i = 0; i < faces.length; i++) {  // Loop thru each face
            for (int j = 0; j < 3; j++) { // Loop thru 3 edges in each face
                int adjacentFace = adjacentFaces[i][j]; // Get adjacent face
                int edgeTile = edgeIndices[i][j]; // Get edge index of current face
                int adjacentEdgeTile = edgeIndices[adjacentFace][j]; // Get the matching tile index of adjacent face

                
                Color sharedColor = faces[i].getTiles()[edgeTile];
                faces[adjacentFace].setTile(adjacentEdgeTile, sharedColor); // Set edge tiles to the same color
            }
        }
    }



    public void printCube() {
        for(int i=0; i<faces.length;i++) {
            System.out.println("Face " + i + ": " + Arrays.toString(faces[i].getTiles()));
        }
    }

    public boolean validateCube() {
        // Each face has 9 tiles
        for (Face face : faces) {
            if (!face.isValid()) {
                return false;
            }
        }

        // Each face should has a color
        for (int i = 0; i < faces.length - 1; i++) {
            for (int j = i + 1; j < faces.length; j++) {
                if (faces[i].getFaceColor() == faces[j].getFaceColor()) {
                    return false; // No faces have the same color
                }
            }
        }

        return true; // if all tests pass, true
    }

    public static void main(String[] args) {
        PyramidRubik pyramid = new PyramidRubik();
        System.out.println("Is the cube valid? " + pyramid.validateCube());
        pyramid.printCube();
    }
}