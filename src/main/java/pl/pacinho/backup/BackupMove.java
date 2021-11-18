package pl.pacinho.backup;

public class BackupMove {

    //    private boolean moveRight() {
//        List<Integer> mergedIdx = new ArrayList<>();
//        List<Integer> rightWallIdx = GameLogic.getWallIdx(WallType.RIGHT);
//        boolean addNewCell = false;
//        int nonAvailableMoveCount = 0;
//        while (nonAvailableMoveCount < SIZE * SIZE) {
//            nonAvailableMoveCount = 0;
//            List<Cell> out = Arrays.stream(gameBoard.getBoard().getComponents()).map(c -> (Cell) c).collect(Collectors.toList());
//            for (int i = out.size() - 1; i >= 0; i--) {
//                Cell cell = out.get(i);
//                int idxNext = i + 1;
//                if (idxNext > (SIZE * SIZE) - 1
//                        || rightWallIdx.contains(i)
//                        || cell.getCellType() == CellType._EMPTY) {
//                    nonAvailableMoveCount++;
//                    continue;
//                }
//                Cell cell1 = out.get(idxNext);
//                if (cell1.getCellType() == CellType._EMPTY) {
//                    replaceCell(i, cell, idxNext);
//                    addNewCell = true;
//                } else if (cell1.getCellType() == cell.getCellType() && !mergedIdx.contains(i)) {
//                    mergedIdx.add(idxNext);
//                    System.out.println("Merge : c1 " + cell1.getCellType() + "c2 " + cell1.getCellType() + " next : " + cell.getCellType().getNext());
//                    replaceCell(i, new Cell(cell.getCellType().getNext()), idxNext);
//                    addNewCell = true;
//                } else {
//                    nonAvailableMoveCount++;
//                }
//            }
//        }
//        refresh();
//        return addNewCell;
//    }
//
//    private boolean moveLeft() {
//        List<Integer> leftWallIdx = GameLogic.getWallIdx(WallType.LEFT);
//        List<Integer> mergedIdx = new ArrayList<>();
//        boolean addNewCell = false;
//        int nonAvailableMoveCount = 0;
//        while (nonAvailableMoveCount < SIZE * SIZE) {
//            nonAvailableMoveCount = 0;
//            List<Cell> out = Arrays.stream(gameBoard.getBoard().getComponents()).map(c -> (Cell) c).collect(Collectors.toList());
//            for (int i = 0; i < out.size(); i++) {
//                Cell cell = out.get(i);
//                int idxNext = i - 1;
//                if (idxNext < 0
//                        || leftWallIdx.contains(i)
//                        || cell.getCellType() == CellType._EMPTY) {
//                    nonAvailableMoveCount++;
//                    continue;
//                }
//                Cell cell1 = out.get(idxNext);
//                if (cell1.getCellType() == CellType._EMPTY) {
//                    replaceCell(i, cell, idxNext);
//                    addNewCell = true;
//                } else if (cell1.getCellType() == cell.getCellType() && !mergedIdx.contains(i)) {
//                    mergedIdx.add(idxNext);
//                    System.out.println("Merge : c1 " + cell1.getCellType() + "c2 " + cell1.getCellType() + " next : " + cell.getCellType().getNext());
//                    replaceCell(i, new Cell(cell.getCellType().getNext()), idxNext);
//                    addNewCell = true;
//                } else {
//                    nonAvailableMoveCount++;
//                }
//            }
//        }
//        refresh();
//        return addNewCell;
//    }
//
//    private boolean moveDown() {
//        boolean addNewCell = false;
//        List<Integer> mergedIdx = new ArrayList<>();
//        int nonAvailableMoveCount = 0;
//        while (nonAvailableMoveCount < SIZE * SIZE) {
//            nonAvailableMoveCount = 0;
//            List<Cell> out = Arrays.stream(gameBoard.getBoard().getComponents()).map(c -> (Cell) c).collect(Collectors.toList());
//            for (int i = out.size() - 1; i >= 0; i--) {
//                Cell cell = out.get(i);
//                int idxNext = i + SIZE;
//                if (idxNext > (SIZE * SIZE) - 1 || cell.getCellType() == CellType._EMPTY) {
//                    nonAvailableMoveCount++;
//                    continue;
//                }
//                Cell cell1 = out.get(idxNext);
//                if (cell1.getCellType() == CellType._EMPTY) {
//                    replaceCell(i, cell, idxNext);
//                    addNewCell = true;
//                } else if (cell1.getCellType() == cell.getCellType() && !mergedIdx.contains(i)) {
//                    mergedIdx.add(idxNext);
//                    System.out.println("Merge : c1 " + cell1.getCellType() + "c2 " + cell1.getCellType() + " next : " + cell.getCellType().getNext());
//                    replaceCell(i, new Cell(cell.getCellType().getNext()), idxNext);
//                    addNewCell = true;
//                } else {
//                    nonAvailableMoveCount++;
//                }
//            }
//        }
//        refresh();
//        return addNewCell;
//    }
//
//    private boolean moveUp() {
//        boolean addNewCell = false;
//        List<Integer> mergedIdx = new ArrayList<>();
//        int nonAvailableMoveCount = 0;
//        while (nonAvailableMoveCount < SIZE * SIZE) {
//            nonAvailableMoveCount = 0;
//            List<Cell> out = Arrays.stream(gameBoard.getBoard().getComponents()).map(c -> (Cell) c).collect(Collectors.toList());
//
//            for (int i = 0; i < out.size(); i++) {
//                Cell cell = out.get(i);
//                int idxNext = i - SIZE;
//                if (idxNext < 0 || cell.getCellType() == CellType._EMPTY) {
//                    nonAvailableMoveCount++;
//                    continue;
//                }
//                Cell cell1 = out.get(idxNext);
//                if (cell1.getCellType() == CellType._EMPTY) {
//                    replaceCell(i, cell, idxNext);
//                    addNewCell = true;
//                } else if (cell1.getCellType() == cell.getCellType() && !mergedIdx.contains(i)) {
//                    mergedIdx.add(idxNext);
//                    System.out.println("Merge : c1 " + cell1.getCellType() + "c2 " + cell1.getCellType() + " next : " + cell.getCellType().getNext());
//                    replaceCell(i, new Cell(cell.getCellType().getNext()), idxNext);
//                    addNewCell = true;
//                } else {
//                    nonAvailableMoveCount++;
//                }
//            }
//        }
//        refresh();
//        return addNewCell;
//    }
}