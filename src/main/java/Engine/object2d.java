package Engine;

import org.joml.Vector3f;

import java.util.List;
import java.util.Vector;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

// child dari ShaderProgram karena segala sesuatu akan msk di shaderprogram dulu
public class object2d extends ShaderProgram{
    List<Vector3f> vertices;

    int vao; //send vertice supaya bisa tampil di layar
    int vbo; // buat nyimpen vertices

    public object2d(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices) {
        super(shaderModuleDataList);
        this.vertices = vertices; //supaya bisa ditampilin di void main
        setupVAOVBO(); //tiap vertice berubah kasi update
    }

//    kalo ga dipanggil (vaovbo) pas diubah" nnti isa error
    public void setupVAOVBO(){
        // set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        // set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        // mengirim vertices ke shader
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(vertices), GL_STATIC_DRAW);
    }

    public void draw(){
        drawSetup();
        //draw vertices
        //opsional
        glLineWidth(10);//mengukur ketebalan
        glPointSize(0); //besar kecil vertex(cmn bisa GL_POINTS)

        //wajib
        //yang sering GL_LINE, LINE_STRIP, LINE_LOOP, LINE_TRIANGLE, TRIANGLE_FAN, GL_POINT
        glDrawArrays(GL_TRIANGLES,0, vertices.size());
    }

    // sini ke vaovbo vert, vert->frag, frag->layar
    public void drawSetup(){
        bind();
        //Bind VBO
        glEnableVertexAttribArray(0); //cmn bisa index
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0,3,GL_FLOAT, false,0,0);
    }




}
