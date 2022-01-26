using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace _2115_Client
{

    class Program
    {
        static int check = 0;
        static NetworkStream networkStream = null;
        static void Main(string[] args)
        {
            TcpClient tcp = null;

            try
            {
                tcp = new TcpClient("10.80.162.111", 13012);
                networkStream = tcp.GetStream();
                Console.WriteLine("### 서버 접속이 완료되었습니다. ###");

                //닉네임
                Console.Write("이름 : ");
                string nickname = Console.ReadLine();
                Byte[] data = System.Text.Encoding.UTF8.GetBytes("Command: setupNickName");
                networkStream.Write(data, 0, data.Length);
                data = new byte[256];
                data = System.Text.Encoding.UTF8.GetBytes(nickname);
                networkStream.Write(data, 0, data.Length);

                //학년
                Console.Write("학년 : ");
                string grade = Console.ReadLine();  
                data = new byte[256];
                data = System.Text.Encoding.UTF8.GetBytes("Command: setupGrade");
                networkStream.Write(data, 0, data.Length);
                data = new byte[256];
                data = System.Text.Encoding.UTF8.GetBytes(grade);
                networkStream.Write(data, 0, data.Length);

                //학반
                Console.Write("학반 : ");
                string inputClass = Console.ReadLine();
                data = new byte[256];
                data = System.Text.Encoding.UTF8.GetBytes("Command: setupClass");
                networkStream.Write(data, 0, data.Length);
                data = new byte[256];
                data = System.Text.Encoding.UTF8.GetBytes(inputClass);
                networkStream.Write(data, 0, data.Length);

                //연결 완료 메세지
                data = System.Text.Encoding.UTF8.GetBytes("Command: conectionSuccess");
                networkStream.Write(data, 0, data.Length);

                Thread sendThread = new Thread(client_Write);
                Thread getThread = new Thread(client_Read);
                sendThread.Start();
                getThread.Start();

                sendThread.Join();
                getThread.Join();

                networkStream.Close();
                tcp.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine("### 서버 접속이 실패하였습니다. ###");
            }

            finally
            {
                networkStream.Close();
                tcp.Close();
            }
        }

        public static bool onClient= true;

        static void client_Write()
        {
            Program input = new Program();

            while (onClient)
            {
                input.input_Message(Console.ReadLine());
            }

            Console.Write("종료되었습니다...");
        }

        // 명령어 종류 0 - 그룹채팅 / 1 - 귓속말 / 2 - 전체메시지 / 3 - 로그아웃 / 4 - 메시지 입력 오류
        public string instruction;
        // 귓속말 일 경우 상대방 닉네임
        public string counterpart;
        // 메시지
        public string message;

        public void input_Message(string input_String)
        {
            input_String = input_String.Trim();
            Byte[] data = null;

            instruction = "";
            counterpart = "";
            message = "";

            // 내용이 없는 경우 경고 메시지 출력
            if (input_String.Length == 0)
            {
                instruction = "Command: error";
                Console.WriteLine("=> 내용을 입력 바랍니다.");
            }

            // 첫번째 글자가 "/" 아닌 경우 -> 명령어 입력이 아닌 경우 그룹 채팅
            else if (input_String.Substring(0, 1) != "/")
            {
                instruction = "Command: GroupMessage";
                message = input_String;

                data = new byte[256];
                data = System.Text.Encoding.UTF8.GetBytes(instruction);
                networkStream.Write(data, 0, data.Length);

                data = new byte[256];
                data = System.Text.Encoding.UTF8.GetBytes(message);
                networkStream.Write(data, 0, data.Length);
            }

            // 첫번째 글자가 "/" 인 경우 -> 명령어 종류에 따라 메시지 구분
            else
            {
                string[] input_String_Split = input_String.Split(' ');

                // 귓속말 형식 처리
                if (input_String_Split[0] == "/귓속말")
                {
                    if (input_String_Split.Length < 3)
                    {
                        instruction = "Command: error";
                        Console.WriteLine("=> [/귓속말 닉네임 내용] 형식으로 입력 바랍니다.");
                    }
                    else
                    {
                        instruction = "Command: Whisper";
                        counterpart = input_String_Split[1];
                        message = input_String_Split[2];

                        data = new byte[256];
                        data = System.Text.Encoding.UTF8.GetBytes(instruction);
                        networkStream.Write(data, 0, data.Length);

                        data = new byte[256];
                        data = System.Text.Encoding.UTF8.GetBytes(counterpart + "|" + message);
                        networkStream.Write(data, 0, data.Length);
                    }
                }
                // 전체 메시지 발송
                else if (input_String_Split[0] == "/전체")
                {
                    if (input_String_Split.Length < 2)
                    {
                        instruction = "Command: error";
                        Console.WriteLine("=> [/전체 내용] 형식으로 입력 바랍니다.");
                    }
                    else
                    {
                        instruction = "Command: Message";
                        message = input_String_Split[1];

                        data = new byte[256];
                        data = System.Text.Encoding.UTF8.GetBytes(instruction);
                        networkStream.Write(data, 0, data.Length);

                        data = new byte[256];
                        data = System.Text.Encoding.UTF8.GetBytes(message);
                        networkStream.Write(data, 0, data.Length);
                    }
                }
                // 프로그램 종료
                else if (input_String_Split[0] == "/종료")
                {
                    instruction = "Command: ConnectEndMessage";
                    data = new byte[256];
                    data = System.Text.Encoding.UTF8.GetBytes(instruction);
                    networkStream.Write(data, 0, data.Length);

                    instruction = "Command: ConnectEnd";
                    data = new byte[256];
                    data = System.Text.Encoding.UTF8.GetBytes(instruction);
                    networkStream.Write(data, 0, data.Length);
                }
                else
                {
                    instruction = "Command: error";
                    Console.WriteLine("=> 명령어 형태에 맞게 작성하시기 바랍니다.");
                    Console.WriteLine("=> [/귓속말 닉네임 내용]");
                    Console.WriteLine("=> [/전체 내용]");
                    Console.WriteLine("=> [/종료]");
                }
            }
        }



        static void client_Read()
        {
            while (true)
            {

                // data 초기화
                Byte[] data = new byte[256];

                // 서버에서 데이터 받아오기
                Int32 read_length = networkStream.Read(data, 0, data.Length);

                // 입력 받은 byte 문자를 utf8 코드를 이용하여 string 형태로 변환
                string read = System.Text.Encoding.UTF8.GetString(data, 0, read_length);

                if (read == "Command: ConnectEndOk")
                {
                    onClient = false;
                    break;
                }

                Console.WriteLine(read);
            }
        }
    }
}
