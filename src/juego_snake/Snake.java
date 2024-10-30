package juego_snake;

import logica.Jugador1;
import logica.Jugador2;
import logica.Jugador;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Snake extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Circle circle1;
    private Circle circle2;

    Jugador1 jugador1 = new Jugador();
    Jugador2 jugador2 = new Jugador();

    private int tile_size = 60;
    private int width = 8;
    private int heigth = 8;

    private boolean iniciar = false;
    private Label dadosResultado;
    private Label turno;

    private int cantSeisJug1 = 0;
    private int cantSeisJug2 = 0;

    private int posicionJugador1 = 1;
    private int posicionJugador2 = 1;

    private boolean turnoJugador1 = true;
    private boolean turnoJugador2 = true;

    private static int posXJugador1 = 30;
    private static int posYjugador1 = 450;

    private static int posXJugador2 = 30;
    private static int posYjugador2 = 450;

    private int[][] posicionesX = new int[8][8];
    private int[][] posicionesY = new int[8][8];
   

    // public boolean inicioJuego = true;
    Button iniciarButton;

    private Group tileGroup = new Group();

    private Parent createConetent() {
        Pane root = new Pane();
        root.setPrefSize(width * tile_size, (heigth * tile_size) + 80);
        root.getChildren().addAll(tileGroup);

        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tile_size, tile_size);
                tile.setTranslateX(j * tile_size);
                tile.setTranslateY(i * tile_size);
                tileGroup.getChildren().add(tile);
                posicionesX[i][j] = j * (tile_size);
                posicionesY[i][j] = i * tile_size;
            }
        }

        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(posicionesX[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                System.out.print(posicionesY[i][j] + " ");
            }
            System.out.println();
        }

        circle1 = new Circle(30);
        circle1.setId("Jugador 1");
        circle1.setFill(Color.BROWN);
       // circle1.getStyleClass().add("juego_snake/style.css");
        circle1.setTranslateX(posXJugador1);
        circle1.setTranslateY(posYjugador1);

        circle2 = new Circle(30);
        circle2.setId("Jugador 2");
        circle2.setFill(Color.GREEN);
       // circle2.getStyleClass().add("juego_snake/style.css");
        circle2.setTranslateX(posXJugador2);
        circle2.setTranslateY(posYjugador2);

        Button jug1 = new Button("Jugador1");
        jug1.setTranslateX(40);
        jug1.setTranslateY(500);
        jug1.setVisible(true);

        turno = new Label("");
        turno.setTranslateX(190);
        turno.setTranslateY(490);

        jug1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (iniciar) {
                    if (turnoJugador1) {
                        dadosResultado.setText(jugador1.PlayJugador());
                        moverJugador1();
                        trasladarJugador(posXJugador1, posYjugador1, circle1);
                        System.out.println(jugador1.cantMov());
                        System.out.println(posicionJugador1);
                        if (snakeAndLadders(1)) {
                            trasladarJugador(posXJugador1, posYjugador1, circle1);
                        }
                        if (jugador1.cantMov() == 6) {
                            cantSeisJug1 += 1;
                            if (cantSeisJug1 == 3) {
                                moverJugador1();
                                trasladarJugador(posXJugador1, posYjugador1, circle1);
                                cantSeisJug1 = 0;
                                //dadosResultado.setText("");
                            } else {
                                turnoJugador2 = false;
                                turnoJugador1 = true;
                                turno.setText("Turno actual: jugador 1 por sacar 6");
                            }
                        } else {
                            turnoJugador1 = false;
                            turnoJugador2 = true;
                            turno.setText("Turno actual: jugador 2");
                        }

                    }
                }
            }

        });

        Button jug2 = new Button("Jugador 2");
        jug2.setTranslateX(380);
        jug2.setTranslateY(500);

        jug2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (iniciar) {
                    if (turnoJugador2) {
                        dadosResultado.setText(jugador2.PlayJugador());
                        moverJugador2();
                        trasladarJugador(posXJugador2, posYjugador2, circle2);
                        System.out.println(jugador2.cantMov());
                        if (snakeAndLadders(2)) {
                            trasladarJugador(posXJugador2, posYjugador2, circle2);
                        }

                        if (jugador2.cantMov() == 6) {
                            cantSeisJug2 += 1;
                            if (cantSeisJug2 == 3) {
                                moverJugador2();
                                trasladarJugador(posXJugador2, posYjugador2, circle2);
                                cantSeisJug2 = 0;
                            } else {
                                turnoJugador2 = true;
                                turnoJugador1 = false;
                                turno.setText("Turno actual: jugador 2 por sacar 6");
                            }
                        } else {
                            turnoJugador1 = true;
                            turnoJugador2 = false;
                            turno.setText("Turno actual: jugador 1");
                        }
                    }
                }
            }

        });

        iniciarButton = new Button("Iniciar Juego");
        iniciarButton.setTranslateX(210);
        iniciarButton.setTranslateY(530);
        iniciarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                iniciar = true;

                posXJugador1 = 30;
                posYjugador1 = 450;

                posXJugador2 = 30;
                posYjugador2 = 450;

                circle1.setTranslateX(posXJugador1);
                circle1.setTranslateY(posYjugador1);
                circle2.setTranslateX(posXJugador2);
                circle2.setTranslateY(posYjugador2);

                turno.setText("Turno actual: Jugador 1");
                turnoJugador1 = true;
                turnoJugador2 = false;
                iniciarButton.setVisible(false);

            }
        });

        dadosResultado = new Label("");
        dadosResultado.setTextFill(Color.BROWN);
        dadosResultado.setTranslateX(190);
        dadosResultado.setTranslateY(510);

        Image img = new Image("juego_snake/snake.png");
        ImageView bgImage = new ImageView();
        bgImage.setImage(img);
        bgImage.setFitHeight(480);
        bgImage.setFitWidth(480);

        tileGroup.getChildren().addAll( circle1, circle2, jug1, jug2, iniciarButton, dadosResultado, turno);
        return root;
    }

    private void moverJugador1() {
        for (int i = 0; i < jugador1.cantMov(); i++) {
            if (cantSeisJug1 == 3) {
                posXJugador1 = 30;
                posYjugador1 = 450;
                break;
            }
            if (posicionJugador1 % 2 == 1) {
                posXJugador1 += 60;
            }
            if (posicionJugador1 % 2 == 0) {
                posXJugador1 -= 60;
            }
            if (posXJugador1 > 450) {
                posYjugador1 -= 60;
                posXJugador1 -= 60;
                posicionJugador1++;
            }
            if (posXJugador1 < 30) {
                posYjugador1 -= 60;
                posXJugador1 += 60;
                posicionJugador1++;
            }

            if (posicionJugador1 == 8) {
                if ((posYjugador1 < 30 || posYjugador1 > 30)) {
                    posXJugador1 = 30;
                    posYjugador1 = 30;
                    dadosResultado.setText("Ha ganado el jugador 1");
                    turno.setText("");
                    turnoJugador1=false;
                    turnoJugador2=false;
                    //iniciar = false;
                    //iniciarButton.setText("Comenzar otra vez");
                    //iniciarButton.setVisible(true);
                }
            }
            System.out.println(posXJugador1);
            System.out.println(posYjugador1);
        }
    }

    private void moverJugador2() {
        for (int i = 0; i < jugador2.cantMov(); i++) {
            if (cantSeisJug2 == 3) {
                posXJugador2 = 30;
                posYjugador2 = 450;
                break;
            }
            if (posicionJugador2 % 2 == 1) {
                posXJugador2 += 60;
            }
            if (posicionJugador2 % 2 == 0) {
                posXJugador2 -= 60;
            }
            if (posXJugador2 > 450) {
                posYjugador2 -= 60;
                posXJugador2 -= 60;
                posicionJugador2++;
            }
            if (posXJugador2 < 30) {
                posYjugador2 -= 60;
                posXJugador2 += 60;
                posicionJugador2++;
            }

            if (posicionJugador2 == 8) {
                if ((posYjugador2 < 30 || posYjugador2 > 30)) {
                    posXJugador2 = 30;
                    posYjugador2 = 30;
                    dadosResultado.setText("Ha ganado el jugador 2");
                    turno.setText("");
                    //iniciar = false;
                    //iniciarButton.setText("Comenzar otra vez");
                    //iniciarButton.setVisible(true);
                }
            }
        }
    }

    private boolean snakeAndLadders(int player) {
        if (player == 1) {
            //Ladders
            if (posXJugador1 == 450 && posYjugador1 == 450) {
                posicionJugador1 = 4;
                posXJugador1 = 390;
                posYjugador1 = 210;
                return true;
            }
            if (posXJugador1 == 270 && posYjugador1 == 390) {
                posicionJugador1 = 4;
                posYjugador1 = 210;
                posXJugador1 = 330;
                return true;
            }
            if (posXJugador1 == 30 && posYjugador1 == 270) {
                posicionJugador1 = 6;
                posXJugador1 = 90;
                posYjugador1 = 150;
                return true;
            }
            if (posXJugador1 == 270 && posYjugador1 == 150) {
                posicionJugador1 = 8;
                posXJugador1 = 210;
                posYjugador1 = 30;
                return true;
            }

            //Snakes
            if (posXJugador1 == 270 && posYjugador1 == 30) {
                posicionJugador1 = 6;
                posXJugador1 = 330;
                posYjugador1 = 150;
                return true;
            }
            if (posXJugador1 == 150 && posYjugador1 == 90) {
                posicionJugador1 = 1;
                posXJugador1 = 270;
                posYjugador1 = 450;
                return true;
            }
            if (posXJugador1 == 450 && posYjugador1 == 150) {
                posicionJugador1 = 3;
                posXJugador1 = 330;
                posYjugador1 = 330;
                return true;
            }
            if (posXJugador1 == 90 && posYjugador1 == 270) {
                posicionJugador1 = 1;
                posXJugador1 = 150;
                posYjugador1 = 450;
                return true;
            }
        }
        if (player == 2) {
            if (posXJugador2 == 450 && posYjugador2 == 450) {
                posicionJugador2 = 4;
                posXJugador2 = 390;
                posYjugador2 = 210;
                return true;
            }
            if (posXJugador2 == 270 && posYjugador2 == 390) {
                posicionJugador2 = 4;
                posYjugador2 = 210;
                posXJugador2 = 330;
                return true;
            }
            if (posXJugador2 == 30 && posYjugador2 == 270) {
                posicionJugador2 = 6;
                posXJugador2 = 90;
                posYjugador2 = 150;
                return true;
            }
            if (posXJugador2 == 270 && posYjugador2 == 150) {
                posicionJugador2 = 8;
                posXJugador2 = 210;
                posYjugador2 = 30;
                return true;
            }

            //Snakes
            if (posXJugador2 == 270 && posYjugador2 == 30) {
                posicionJugador2 = 6;
                posXJugador2 = 330;
                posYjugador2 = 150;
                return true;
            }
            if (posXJugador2 == 150 && posYjugador2 == 90) {
                posicionJugador2 = 1;
                posXJugador2 = 270;
                posYjugador2 = 450;
                return true;
            }
            if (posXJugador2 == 450 && posYjugador2 == 150) {
                posicionJugador2 = 3;
                posXJugador2 = 330;
                posYjugador2 = 330;
                return true;
            }
            if (posXJugador2 == 90 && posYjugador2 == 270) {
                posicionJugador2 = 1;
                posXJugador2 = 150;
                posYjugador2 = 450;
                return true;
            }
        }
        return false;
    }

    private void trasladarJugador(int x, int y, Circle c) {
        TranslateTransition animar = new TranslateTransition(Duration.millis(1000), c);
        animar.setToX(x);
        animar.setToY(y);
        animar.setAutoReverse(false);
        animar.play();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = new Scene(createConetent());

        stage.setTitle("Serpientes y Escaleras");
        stage.setScene(scene);
        stage.show();

        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
