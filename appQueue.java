package pQueue;

import java.util.Scanner;

class Order {

    String nama;
    String item;
    int price;
    int qty;

    Order(String n, String i, int p, int q) {
        nama = n;
        item = i;
        qty = q;
        price = p;

    }

    String getNama() {
        return nama;
    }

    String getItem() {
        return item;
    }

    int getPrice() {
        return price;
    }

    int getQty() {
        return qty;
    }

}

class Node {

    Order order;
    Node next;

    // Constructor untuk mempermudah memasukkan data Order ke dalam Node
    Node(Order Order) {
        this.order = Order;
        this.next = null;

    }

}

class Queue {

    Node front, rear;
    int count, total;

    Queue() {
        front = rear = null;
    }

    void enqueue(Node OrderNode) {
        if (rear == null) {
            front = rear = OrderNode;

        } else {
            rear.next = OrderNode;
            rear = OrderNode;
        }
        total = total + (OrderNode.order.price * OrderNode.order.qty);

    }

    Node dequeue() {
        Node t = front;
        if (t == null) {
            System.out.println("Antrian kosong!!!");
            return null;
        } else if (t.next == null) {
            front = rear = null;
        } else {
            front = front.next;
            t.next = null;
        }

        System.out.println(t.order.item + "out..");
        return t;
    }

    void view() {
        System.out.println("Order queue");
        for (Node t = front; t != null; t = t.next) {
            System.out.print("[" + t.order.nama + ",");
            System.out.print(t.order.item + "," + t.order.price + ",");
            System.out.println(t.order.qty + "]");
        }
        System.out.println();
    }
}

public class appQueue {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue queue = new Queue();
        int choice = 0;
        String yorn = "";
        do {
            System.out.println("App Order ");
            System.out.println("1.Order\n2.Process\n3.View");
            System.out.println("4.Exit");
            System.out.print("Choice = ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Nama Pembeli : ");
                    String pembeli = sc.next();
                    //loop choosing the item
                    do{
                    System.out.println("List Item : ");
                    System.out.println("1.Sepatu\n2.Sandal");
                    System.out.println("3.Jaket");
                    System.out.print("Choice = ");
                    int choice2 = sc.nextInt();
                    String item = "";
                    int prc= 0;
                    if (choice2 == 1) {
                        item = "Sepatu"; prc = 200000;
                    } else if (choice2 == 2) {
                        item = "Sandal";prc = 25000;
                    } else if (choice2 == 3) {
                        item = "Jaket"; prc = 350000;
                    }
                    System.out.print("Quantity : ");
                    int qty = sc.nextInt();
                    Order order = new Order(pembeli, item, prc, qty);
                    Node node = new Node(order);
                    node.order = order;
                    queue.enqueue(node);
                    //input : beli lagi? 1.yes or 0.no
                    System.out.print("Tambahkan barang lain? (y/n)");
                    yorn = sc.next();
                    sc.nextLine();
            } while(!yorn.equalsIgnoreCase("n"));
                    
                    //end loop
                    break;
                case 2:
                    // Memanggil fungsi dequeue yang sekarang mengembalikan objek Node
                    Node keluar = queue.dequeue();
                    break;
                case 3:
                    // Menampilkan antrian saat ini
                    queue.view();
                    break;
                case 4:
                    System.out.println("Keluar dari program. Terima kasih JANCOOKK!!!");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia!");
            }
        } while (choice != 4);
    }
}
