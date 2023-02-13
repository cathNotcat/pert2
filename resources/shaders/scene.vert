#version 330

layout(location=0) in vec3 position;

void main() {
// agar bisa disimpan dilayar, kasi variable gl_Position (gabisa di ganti" karena pny gsl nya lgsung)
    gl_Position = vec4(position, 1.0);
}
