import Engine.ShaderProgram;
import Engine.Window;
import Engine.object2d;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;



public class Main {
    private Window window =
            new Window(800,800,"Hello World");

    ArrayList<object2d> objects = new ArrayList<>();
    public void run() {
        init();
        loop();

        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }

    public void init() {
        window.init();
        GL.createCapabilities();

        //code taro setelah init dan GL
        objects.add(new object2d(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(0,.5f,0),
                                new Vector3f(-0.5f,-0.5f, 0),
                                new Vector3f(0.5f, -0.5f, 0)
                        )
                )
        ));
    }

    public void loop() {
//        kalo ga pake loop nnti habis buka window baru langsung ketutup (ga bisa tambahin frame)
        while(window.isOpen()){
            window.update();
//            warna hanya 0 - 1 pake f
            glClearColor(0,0,0,0);
            GL.createCapabilities();

            //code
            //..
            for(object2d object:objects){
                object.draw();
            }

            glDisableVertexAttribArray(0);
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new Main().run();

    }
}