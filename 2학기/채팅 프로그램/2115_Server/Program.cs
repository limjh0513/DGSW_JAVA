using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace _2115_Server
{
    class Client_Class
    {
        private NetworkStream networkStream = null;
        private TcpClient server_client = null;

        private Thread threadRead = null;

        private string userNickName = "";
        private string userGrade = "";
        private string userClass = "";
        public Client_Class(TcpClient server_client)
        {
            this.server_client = server_client;
            this.networkStream = this.server_client.GetStream();

            threadRead = new Thread(server_Read);
            threadRead.Start();
        }

        private void server_Read()
        {
            try
            {
                while (true)
                {
                    //Command 메시지 수신
                    Byte[] data = new byte[256];
                    Int32 read_length = networkStream.Read(data, 0, data.Length);
                    String read_Command = System.Text.Encoding.UTF8.GetString(data, 0, read_length);

                    if (read_Command == "Command: setupNickName")
                    { //닉네임 수신 및 지정 명령어
                        data = new byte[256];
                        read_length = networkStream.Read(data, 0, data.Length);
                        this.userNickName = System.Text.Encoding.UTF8.GetString(data, 0, read_length);
                    }
                    else if (read_Command == "Command: setupGrade")
                    { //닉네임 수신 및 지정 명령어
                        data = new byte[256];
                        read_length = networkStream.Read(data, 0, data.Length);
                        this.userGrade = System.Text.Encoding.UTF8.GetString(data, 0, read_length);
                    }
                    else if (read_Command == "Command: setupClass")
                    { //닉네임 수신 및 지정 명령어
                        data = new byte[256];
                        read_length = networkStream.Read(data, 0, data.Length);
                        this.userClass = System.Text.Encoding.UTF8.GetString(data, 0, read_length);
                    }
                    else if (read_Command == "Command: GroupMessage")
                    { //그룹 메세지
                        data = new byte[256];
                        read_length = networkStream.Read(data, 0, data.Length);

                        string read = System.Text.Encoding.UTF8.GetString(data, 0, read_length);

                        string message = this.userNickName + ": " + read;
                        data = System.Text.Encoding.UTF8.GetBytes(message);
                        foreach (Client_Class temp_class in Program.client_list)
                        {
                            if (temp_class.userGrade == this.userGrade && temp_class.userClass == this.userClass)
                                temp_class.networkStream.Write(data, 0, data.Length);
                        }
                    }
                    else if (read_Command == "Command: conectionSuccess")
                    { //연결 성공시
                        string read = this.userGrade + "학년 " + this.userClass + "반 그룹채팅방에 " + this.userNickName + " 님이 입장하였습니다.";
                        data = System.Text.Encoding.UTF8.GetBytes(read);
                        Console.WriteLine(read);

                        foreach (Client_Class temp_class in Program.client_list)
                        {
                            if (temp_class.userGrade == this.userGrade && temp_class.userClass == this.userClass)
                                temp_class.networkStream.Write(data, 0, data.Length);
                        }
                    }
                    else if (read_Command == "Command: Message")
                    { //메세지 수신 및 처리 명령어

                        //클라이언트 데이터 수신
                        data = new byte[256];
                        read_length = networkStream.Read(data, 0, data.Length);

                        string read = System.Text.Encoding.UTF8.GetString(data, 0, read_length);
                        read = "[전체] " + this.userNickName + "(" + this.userGrade + "-" + this.userClass + ")" + ": " + read;
                        data = System.Text.Encoding.UTF8.GetBytes(read);
                        Console.WriteLine("\n" + read);

                        foreach (Client_Class temp_class in Program.client_list)
                        {
                            temp_class.networkStream.Write(data, 0, data.Length);
                        }
                    }
                    else if (read_Command == "Command: Whisper")
                    { //귓속말
                        data = new byte[256];
                        read_length = networkStream.Read(data, 0, data.Length);
                        bool sendOk = false; //귓속말 보내기 성공

                        string read = System.Text.Encoding.UTF8.GetString(data, 0, read_length);

                        string[] whisper_message = read.Split('|');

                        data = System.Text.Encoding.UTF8.GetBytes("[귓속말]" + this.userNickName + " -> " + whisper_message[0] + ": " + whisper_message[1]);

                        foreach (Client_Class tep_client in Program.client_list)
                        {
                            if (tep_client.userNickName == whisper_message[0])
                            {
                                tep_client.networkStream.Write(data, 0, data.Length);
                                sendOk = true;
                            }
                        }
                        if (!sendOk)
                        {
                            data = System.Text.Encoding.UTF8.GetBytes("[귓속말실패]" + this.userNickName + " -> " + whisper_message[0] + ": " + whisper_message[1]);
                            networkStream.Write(data, 0, data.Length);
                        }
                    }
                    else if (read_Command == "Command: ConnectEnd")
                    { //연결 종료
                        data = System.Text.Encoding.UTF8.GetBytes("Command: ConnectEndOk");
                        networkStream.Write(data, 0, data.Length);

                        networkStream.Close();
                        server_client.Close();

                        Program.client_list.Remove(this);
                    }
                }
            }
            catch
            {
                string read = this.userGrade + "학년 " + this.userClass + "반 그룹채팅방에서 " + this.userNickName + " 님이 나가셨습니다.";
                Byte[] data = System.Text.Encoding.UTF8.GetBytes(read);
                Console.WriteLine(read);

                Program.client_list.Remove(this);

                foreach (Client_Class temp_class in Program.client_list)
                {
                    if (temp_class.userGrade == this.userGrade && temp_class.userClass == this.userClass)
                        temp_class.networkStream.Write(data, 0, data.Length);
                }
                networkStream.Close();
                server_client.Close();
                Program.client_list.Remove(this);
                Console.WriteLine("### 클라이언트가 종료되었습니다 ###");
            }
        }
    }

    class Program
    {

        public static ArrayList client_list = new ArrayList();
        static void Main(string[] args)
        {
            TcpListener tcp_server = new TcpListener(IPAddress.Any, 13012);
            tcp_server.Start();

            Console.WriteLine("     ### 서버 실행 ###");

            while (true)
            {
                Console.WriteLine("### 클라이언트 대기중 ###");
                TcpClient server_client = tcp_server.AcceptTcpClient();
                Client_Class client_user = new Client_Class(server_client);
                client_list.Add(client_user);
                Console.WriteLine("### 클라이언트가 접속 되었습니다 ###");
            }

            //Console.WriteLine("종료...\n");
            //tcp_server.Stop();
        }

    }
}

