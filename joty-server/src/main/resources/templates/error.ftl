<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link Not Found</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            overflow: hidden;
        }

        .container {
            text-align: center;
            padding: 20px;
            max-width: 600px;
            width: 90%;
            animation: fadeIn 1s ease-in-out;
        }

        h1 {
            font-size: 3.5rem;
            margin-bottom: 20px;
            color: #e63946;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
        }

        p {
            font-size: 1.2rem;
            line-height: 1.6;
            margin-bottom: 20px;
            color: #555;
        }

        .error-code {
            font-size: 6rem;
            font-weight: bold;
            color: #1d3557;
            margin-bottom: 10px;
            animation: scaleIn 0.8s ease-in-out;
        }

        .highlight {
            color: #457b9d;
            font-weight: bold;
        }

        a {
            color: #457b9d;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #1d3557;
            text-decoration: underline;
        }

        .illustration {
            margin-bottom: 20px;
        }

        .illustration svg {
            max-width: 200px;
            height: auto;
        }

        /* Animation Keyframes */
        @keyframes fadeIn {
            0% { opacity: 0; transform: translateY(20px); }
            100% { opacity: 1; transform: translateY(0); }
        }

        @keyframes scaleIn {
            0% { transform: scale(0.8); opacity: 0; }
            100% { transform: scale(1); opacity: 1; }
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            h1 {
                font-size: 2.5rem;
            }

            .error-code {
                font-size: 4rem;
            }

            p {
                font-size: 1rem;
            }

            .illustration svg {
                max-width: 150px;
            }
        }

        @media (max-width: 480px) {
            h1 {
                font-size: 2rem;
            }

            .error-code {
                font-size: 3rem;
            }

            .illustration svg {
                max-width: 120px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="illustration">
        <svg viewBox="0 0 24 24" fill="none" stroke="#e63946" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M10.29 3.86L1.82 18a2 2 0 0 0 1.71 3h16.94a2 2 0 0 0 1.71-3L13.71 3.86a2 2 0 0 0-3.42 0z"/>
            <line x1="12" y1="9" x2="12" y2="13"/>
            <line x1="12" y1="17" x2="12.01" y2="17"/>
        </svg>
    </div>
    <div class="error-code">404</div>
    <h1>链接不存在～</h1>
    <p>抱歉，您访问的短链接不存在或已失效。请检查链接是否正确，或返回。</p>
</div>
</body>
</html>
